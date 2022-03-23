#include <stdio.h>
#include <stdint.h>

#include "libsdf.h"

// extra possible varaibles declared in the model
volatile double **system_img_source;
volatile double **system_img_sink;
volatile uint16_t dimX;
volatile uint16_t dimY;

int main()
{
    // generate signals based on names
    double GrayScaleToGetPx[6];
    uint16_t GraysScaleX[1];
    uint16_t GraysScaleY[1];
    uint16_t AbsX[1];
    uint16_t AbsY[1];
    uint16_t GrayScaleToAbs[2];
    double gxsig[6];
    double gysig[6];
    double absxsig[1];
    double absysig[1];

    double_channel_t GrayScaleToGetPx_channel;
    uint16_t_channel_t GraysScaleX_channel;
    uint16_t_channel_t GraysScaleY_channel;
    uint16_t_channel_t AbsX_channel;
    uint16_t_channel_t AbsY_channel;
    uint16_t_channel_t GrayScaleToAbs_channel;
    double_channel_t gxsig_channel;
    double_channel_t gysig_channel;
    double_channel_t absxsig_channel;
    double_channel_t absysig_channel;

    GrayScaleToGetPx_channel = create_channel_double(GrayScaleToGetPx, 6);
    GraysScaleX_channel = create_channel_uint16_t(GraysScaleX, 1);
    GraysScaleY_channel = create_channel_uint16_t(GraysScaleY, 1);
    AbsX_channel = create_channel_uint16_t(AbsX, 1);
    AbsY_channel = create_channel_uint16_t(AbsY, 1);
    GrayScaleToAbs_channel = create_channel_uint16_t(GrayScaleToAbs, 2);
    gxsig_channel = create_channel_double(gxsig, 6);
    gysig_channel = create_channel_double(gysig, 6);
    absxsig_channel = create_channel_double(absxsig, 1);
    absysig_channel = create_channel_double(absysig, 1);

    // populate with initialValues
    write_token_non_blocking_uint16_t(&GraysScaleX_channel, 0); // based on ZeroValue
    write_token_non_blocking_uint16_t(&GraysScaleY_channel, 0); // based on ZeroValue
    write_token_non_blocking_uint16_t(&AbsX_channel, 0);        // based on ZeroValue
    write_token_non_blocking_uint16_t(&AbsY_channel, 0);        // based on ZeroValue

    // enter infinite loop
    while (1)
    {
        // the loop is constructed based on the PASSedSDFComb trait
        fire_GrayScale(
            system_img_sink,
            dimX,
            dimY,
            &GraysScaleX_channel,
            &GraysScaleY_channel,
            &GraysScaleX_channel,
            &GraysScaleY_channel,
            &GrayScaleToAbs_channel,
            &GrayScaleToGetPx_channel);
        fire_getPx(&GrayScaleToGetPx_channel, &gxsig_channel, &gysig_channel);
        fire_Gx(&gxsig_channel, &absxsig_channel);
        fire_Gy(&gysig_channel, &absysig_channel);
        fire_Abs(
            system_img_sink,
            &GrayScaleToAbs_channel,
            &AbsX_channel,
            &AbsY_channel,
            &AbsX_channel,
            &AbsY_channel,
            &absxsig_channel,
            &absysig_channel);
    }
}