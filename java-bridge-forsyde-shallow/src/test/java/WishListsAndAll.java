import forsyde.io.bridge.forsyde.shallow.drivers.ForSyDeShallowDriver;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class WishListsAndAll {

    public static String item1 = """
            import ForSyDe.Shallow
            import Data.Int
                        
            app1 :: Signal Int8 -> Signal Int8
            app1 s_in = s_out where
              s_1   = actorA s_in
              s_out = actorB s_1
                        
            actorA :: Signal Int8 -> Signal Int8
            actorA = actor11SDF 1 2 f where
              f :: [Int8] -> [Int8]
              f [x] = [-x, x]
              f _   = error "Not enough tokens"
             
            actorB :: Signal Int8 -> Signal Int8
            actorB = actor11SDF 3 2 f where
              f :: [Int8] -> [Int8]
              f [x_1, x_2, x_3] = [x_1 + x_2, x_2 + x_3]
              f _               = error "Not enough tokens"
            """;

    public static String item2 = """
    module SDF_APP2 where
    
    import ForSyDe.Shallow
    import Data.Int
    
    app2 :: Signal Int8 -> Signal Int8
    app2 s_in = s_out
       where s_1          = actorC s_in s_4
             (s_2, s_out) = actorD s_1
             s_3          = actorE s_2
             s_4          = delaySDF [0] s_3
    
    actorC :: Signal Int8 -> Signal Int8 -> Signal Int8
    actorC = actor21SDF (2,1) 1 f where
      f :: [Int8] -> [Int8] -> [Int8]
      f [w_1, w_2] [x] = [w_1 + w_2 + x]
      f _          _   = error "Not correct number of input tokens"
    
    actorD :: Signal Int8 -> (Signal Int8, Signal Int8)
    actorD = actor12SDF 1 (1,1) f where
      f :: [Int8] -> ([Int8], [Int8])
      f [x] = ([x], [2*x])
      f _   = error "Not correct number of input tokens"
    
    actorE :: Signal Int8 -> Signal Int8
    actorE = actor11SDF 1 1 f where
      f :: [Int8] -> [Int8]
      f [x] = [2*x]
      f _   = error "Two few input tokens"
    """;

    public static String item3 = """
    module SDF_APP3 where

    import ForSyDe.Shallow
    import Data.Int

    app3 :: Signal Int8 -> Signal Int8
    app3 s_in = s_out
      where (s_1, s_2) = actorF s_in
            s_3 = actorG s_1
            s_4 = actorH s_2
            s_out = actorI s_3 s_4

    actorF :: Signal Int8 -> (Signal Int8, Signal Int8)
    actorF = actor12SDF 1 (1,1) f where
      f :: [Int8] -> ([Int8], [Int8])
      f [x] = ([x-1], [x+1])
      f _   = error "Not enough tokens"

    actorG :: Signal Int8 -> Signal Int8
    actorG = actor11SDF 1 1 f where
      f :: [Int8] -> [Int8]
      f [x] = [2*x]
      f _   = error "Not enough tokens"

    actorH :: Signal Int8 -> Signal Int8
    actorH = actor11SDF 1 1 f where
      f :: [Int8] -> [Int8]
      f [x] = [x+1]
      f _   = error "Not enough tokens"

    actorI :: Signal Int8 -> Signal Int8 -> Signal Int8
    actorI = actor21SDF (1,1) 1 f where
      f :: [Int8] -> [Int8] -> [Int8]
      f [x] [y] = [x+y]
      f _   _   = error "Not enough tokens"
    """;

    public static void main(String[] args) throws Exception {
        var driver = new ForSyDeShallowDriver();
        var model = driver.loadModel(new ByteArrayInputStream(item1.getBytes()));
        System.out.println(model);
        var model2 = driver.loadModel(new ByteArrayInputStream(item2.getBytes()));
        System.out.println(model2);
        var model3 = driver.loadModel(new ByteArrayInputStream(item3.getBytes()));
        System.out.println(model3);
    }
}
