{-# OPTIONS_HADDOCK hide #-}
-----------------------------------------------------------------------------
-- |
-- Module      :  ForSyDe.IO.Examples.SDF.Complete
-- Copyright   :  (c) George Ungureanu, 2018
--                (c) Rodolfo Jordao, 2022
-- License     :  BSD-style (see the file LICENSE)
--
-- adapted from https://github.com/forsyde/forsyde-shallow-examples/tree/master/src/ForSyDe/Shallow/Example/SDF/ImageProcessing
-- Contains the image processing functions as well as the DUT process
-- network instantiation.
-----------------------------------------------------------------------------
module ForSyDe.IO.Examples.SDF.Complete where

import ForSyDe.Shallow


-- | Type alias for image as matrix
type Image a = Vector (Vector a)

-- | the /map (farm) parallel pattern/ with one input, on matrices.
mapMatrix     = mapV . mapV

-- | the /map (farm) parallel pattern/ with two inputs, on matrices.
zipWithMatrix = zipWithV . zipWithV

-- | the /reduce parallel pattern/ on matrices.
reduceMatrix f = reduceV f . mapV (reduceV f)

lengthX :: Vector (Vector a) -> Int
lengthX = lengthV . headV

lengthY :: Vector (Vector a) -> Int
lengthY = lengthV


-- | Parallel communication (data access) pattern which "rotates" a
-- matrix. The rotation is controled with the /x/ and /y/ index
-- arguments as following:
--
-- * @(> 0)@ : rotates the matrix right/down with the corresponding
-- number of positions.
--
-- * @(= 0)@ : does not modify the position for that axis.
--
-- * @(< 0)@ : rotates the matrix left/up with the corresponding
-- number of positions.
rotateMatrix :: Int -- ^ index on X axis
             -> Int -- ^ index on Y axis
             -> Vector (Vector a)
             -> Vector (Vector a)
rotateMatrix x y = rotateV y . mapV (rotateV x)

-- | Partitions a list of values into a list of list of values,
-- similar to a matrix. See 'toImage'.
partition :: Int -> Int -> [a] -> [[a]]
partition x y list
  | y - length image > 1 = error "image dimention Y mismatch"
  | otherwise = image
  where
    image = groupEvery x list

groupEvery n [] = []
groupEvery n l | length l < n = error "input P3 PPM image is ill-formed"
               | otherwise    = take n l : groupEvery n (drop n l)

-- | Converts a list intro an 'ImageProcessing.Image' (i.e. matrix).
toImage :: Int               -- ^ number of columns (X dimension)
        -> Int               -- ^ number of rows (Y dimension)
        -> [a]               -- ^ list of pixels
        -> Vector (Vector a) -- ^ 'ImageProcessing.Image' of pixels
toImage x y = vector . map vector . partition x y

-- | Converts an 'ImageProcessing.Image' back to a list. See
-- 'toImage'.
fromImage =  concatMap fromVector . fromVector

-- | Applies a function of images on a list. It basically performs
--
-- fromImage . function . toImage x y
wrapImageF :: Int -- ^ X dimension
           -> Int -- ^ Y dimension
           -> (Vector (Vector a) -> Vector (Vector b))
           -- ^ function of images
           -> [a] -- ^ Input list
           -> [b] -- ^ Output list
wrapImageF x y f = fromImage . f . toImage x y


-- | List with grayscale levels encoded as ASCII characters
asciiLevels :: [Char]
asciiLevels = [' ','.',':','-','=','+','/','t','z','U','w','*','0','#','%','@']

------------------------------------------------------------
--              Image processing functions                --
------------------------------------------------------------

-- | Converts an image of pixels into its grayscale equivalent using
-- the formula <<files/images/grayscale.png>>
grayscale :: Image Int -> Image Double
grayscale = mapMatrix (convert . fromVector) . mapV (groupV 3)
  where
    convert [r,g,b] = fromIntegral r * 0.3125
                    + fromIntegral g * 0.5625
                    + fromIntegral b * 0.125
    convert _ = error "X length is not a multiple of 3"

-- | Performs edge detection using the
-- <https://en.wikipedia.org/wiki/Sobel_operator Sobel operator>.
--
-- Edges are areas with strong intensity contrasts in an image. This
-- algorithm calculates abrupt changes and their magnitude, based on
-- the gradient of intensity at each point in the image. It is
-- specified using a /stencil parallel pattern/, which:
--
-- * performs <https://en.wikipedia.org/wiki/Convolution convolution>
-- of the original image with two kernel matrices /Kx/ and /Ky/, one
-- for detecting changes in the /x/-direction and one for the
-- /y/-direction. This results in two gradient matrices /Gx/ and /Gy/.
--
-- * combines the two approximations to obtain the gradient magnitude
-- /G/.
--
-- * scales the magnitude matrix to be correctly represented in
-- grayscale format.
--
-- <<files/images/sobel.png>>
sobel :: Image Double -> Image Double
sobel img = dropMargins $ zipWithMatrix (\x y -> sqrt (x ** 2 + y ** 2)) gx gy
  where
    dropMargins = mapV (tailV . initV) . tailV . initV
    gx = reduceV (zipWithMatrix (+)) (vector [gx11, gx13, gx21, gx23, gx31, gx33])
    gy = reduceV (zipWithMatrix (+)) (vector [gy11, gy12, gy13, gy31, gy32, gy33])
    ------------------------------------------------------
    gx11 = mapMatrix (* (-1)) (rotateMatrix   1    1  img)
--  gx12 = mapMatrix (*   0 ) (rotateMatrix   0    1  img)
    gx13 = mapMatrix (*   1 ) (rotateMatrix (-1)   1  img)
    gx21 = mapMatrix (* (-2)) (rotateMatrix   1    0  img)
--  gx22 = mapMatrix (*   0 ) img
    gx23 = mapMatrix (*   2 ) (rotateMatrix (-1)   0  img)
    gx31 = mapMatrix (* (-1)) (rotateMatrix   1  (-1) img)
--  gx32 = mapMatrix (*   0 ) (rotateMatrix   0  (-1) img)
    gx33 = mapMatrix (*   1 ) (rotateMatrix (-1) (-1) img)
    ------------------------------------------------------
    gy11 = mapMatrix (* (-1)) (rotateMatrix   1    1  img)
    gy12 = mapMatrix (* (-2)) (rotateMatrix   0    1  img)
    gy13 = mapMatrix (* (-1)) (rotateMatrix (-1)   1  img)
--  gy21 = mapMatrix (*   0 ) (rotateMatrix   1    0  img)
--  gy22 = mapMatrix (*   0 ) img
--  gy23 = mapMatrix (*   0 ) (rotateMatrix   1    0  img)
    gy31 = mapMatrix (*   1 ) (rotateMatrix   1  (-1) img)
    gy32 = mapMatrix (*   2 ) (rotateMatrix   0  (-1) img)
    gy33 = mapMatrix (*   1 ) (rotateMatrix (-1) (-1) img)

-- | Converts a 256-value grayscale image to a 16-value ASCII art
-- image.
toAsciiArt :: Image Double -> Image Char
toAsciiArt = mapMatrix num2char
  where
    num2char n = asciiLevels !! level n
    level n = truncate $ nLevels * (n / 255)
    nLevels = fromIntegral $ length asciiLevels - 1

-------------------------------------------------------------
--                SDF process network                      --
-------------------------------------------------------------

-- | SDF process network chaining a series of image processing
-- algorithms upon a stream of pixel values originating from a
-- <https://en.wikipedia.org/wiki/Netpbm_format PPM image>.
imageProcessing :: Int         -- ^ dimension X of the input image
                -> Int         -- ^ dimension Y of the input image
                -> Signal Int  -- ^ Input stream of pixel values
                -> Signal Char -- ^ Output stream of ASCII characters
imageProcessing dimX dimY =  asciiSDF . sobelSDF . graySDF
  where
    graySDF  = actor11SDF (x0 * y1) (x1 * y1) (wrapImageF x0 y1 grayscale )
    sobelSDF = actor11SDF (x2 * y2) (x3 * y3) (wrapImageF x2 y2 sobel     )
    asciiSDF = actor11SDF (x3 * y3) (x3 * y3) (wrapImageF x3 y3 toAsciiArt)

    ------------------------------------------------------------
    -- Production / consumption rates
    ------------------------------------------------------------
    x0 = dimX * 3
    x1 = dimX
    y1 = dimY
    x2 = dimX
    y2 = dimY
    x3 = dimX - 2
    y3 = dimY - 2


