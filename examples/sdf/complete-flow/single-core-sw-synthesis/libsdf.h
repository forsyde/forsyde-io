#ifndef LIBSDF_
#define LIBSDF_

#include <stdlib.h>
#include <stdint.h>

// generated for token of type DoubleType
typedef struct
{
    double *buffer;
    size_t front;
    size_t rear;
    size_t size;
} double_channel_t;

/*
    create a _circular channel of type DoubleType
*/
double_channel_t create_channel_double(double *buffer, size_t size)
{
    double_channel_t channel;
    channel.buffer = buffer;
    channel.size = size;
    channel.front = 0;
    channel.rear = 0;
    return channel;
};

/*
    read a token from channel. The token is of type DoubleType

*/
int read_token_non_blocking_double(double_channel_t *channel, double* data)
{
    //double data = NULL;
    if (channel->front != channel->rear)
    {
        *data = channel->buffer[channel->front];
        // printf("buffer absxsig: before read, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
        channel->front = (channel->front + 1) % channel->size;
        // printf("buffer absxsig: after read, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
        return 0;
    } else {
        return -1;
    }
};

/*
    write a token to _circular absxsig.
*/
int write_token_non_blocking_double(double_channel_t *channel, double data)
{
    /*if the buffer is full*/
    if ((channel->rear + 1) % channel->size == channel->front)
    {
        // full!
        // discard the data
        // printf("buffer full error\n!");
        return -1;
    }
    else
    {
        channel->buffer[channel->rear] = data;
        // printf("buffer absxsig:before write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
        channel->rear = (channel->rear + 1) % channel->size;
        // printf("buffer absxsig:after write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
        return 0;
    }
};

// generated for token of type UInt16
typedef struct
{
    uint16_t *buffer;
    size_t front;
    size_t rear;
    size_t size;
} uint16_t_channel_t;

/*
    create a _circular channel of type uint16_tType
*/
uint16_t_channel_t create_channel_uint16_t(uint16_t *buffer, size_t size)
{
    uint16_t_channel_t channel;
    channel.buffer = buffer;
    channel.size = size;
    channel.front = 0;
    channel.rear = 0;
    return channel;
};

/*
    read a token from channel. The token is of type uint16_tType

*/
int read_token_non_blocking_uint16_t(uint16_t_channel_t *channel, uint16_t* data)
{
    //uint16_t data = NULL;
    if (channel->front != channel->rear)
    {
        *data = channel->buffer[channel->front];
        // printf("buffer absxsig: before read, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
        channel->front = (channel->front + 1) % channel->size;
        // printf("buffer absxsig: after read, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
        return 0;
    } else {
        return -1;
    }
};

/*
    write a token to _circular absxsig.
*/
int write_token_non_blocking_uint16_t(uint16_t_channel_t *channel, uint16_t data)
{
    /*if the buffer is full*/
    if ((channel->rear + 1) % channel->size == channel->front)
    {
        // full!
        // discard the data
        // printf("buffer full error\n!");
        return -1;
    }
    else
    {
        channel->buffer[channel->rear] = data;
        // printf("buffer absxsig:before write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
        channel->rear = (channel->rear + 1) % channel->size;
        // printf("buffer absxsig:after write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
        return 0;
    }
}

// generated due to the SDF actors present in the model
void fire_GrayScale(
    volatile double **system_img_source, // this comes from GrayScale Being a Source
    volatile uint16_t dimX,              // this comes from GrayScale Being a Source
    volatile uint16_t dimY,              // this comes from GrayScale Being a Source
    uint16_t_channel_t *offsetXIn_channel,
    uint16_t_channel_t *offsetYIn_channel,
    uint16_t_channel_t *offsetXOut_channel,
    uint16_t_channel_t *offsetYOut_channel,
    uint16_t_channel_t *dimsOut_channel,
    double_channel_t *gray_channel)
{
    // actor to function connetions derive from connections
    //  edge [] from GrayScale port combFunctions to GrayScaleImpl
    //  edge [] from GrayScale port offsetXIn to GrayScaleImpl port offsetX
    //  edge [] from GrayScale port system_img_source to GrayScaleImpl port system_img_source_address
    //  edge [] from GrayScale port dimX to GrayScaleImpl port dimX
    //  edge [] from GrayScale port dimY to GrayScaleImpl port dimY
    //  edge [] from GrayScaleImpl port offsetXOut to GrayScale port offsetX
    //  edge [] from GrayScale port offsetYIn to GrayScaleImpl port offsetY
    //  edge [] from GrayScaleImpl port offsetYOut to GrayScale port offsetY
    //  edge [] from GrayScaleImpl port gray to GrayScale port gray
    unsigned int i;
    volatile double **system_img_source_address = system_img_source;
    double gray[6];
    uint16_t dimsOut[2];
    uint16_t offsetX;
    uint16_t offsetY;
    read_token_non_blocking_uint16_t(offsetXIn_channel, &offsetX);
    read_token_non_blocking_uint16_t(offsetYIn_channel, &offsetY);
    gray[0] =
        0.3125 * system_img_source_address[offsetY + 0][offsetX + 0] +
        0.5625 * system_img_source_address[offsetY + 0][offsetX + 1] +
        0.125 * system_img_source_address[offsetY + 0][offsetX + 2];
    gray[1] =
        0.3125 * system_img_source_address[offsetY + 0][offsetX + 2] +
        0.5625 * system_img_source_address[offsetY + 0][offsetX + 3] +
        0.125 * system_img_source_address[offsetY + 0][offsetX + 4];
    gray[2] =
        0.3125 * system_img_source_address[offsetY + 1][offsetX + 0] +
        0.5625 * system_img_source_address[offsetY + 1][offsetX + 1] +
        0.125 * system_img_source_address[offsetY + 1][offsetX + 2];
    gray[3] =
        0.3125 * system_img_source_address[offsetY + 1][offsetX + 2] +
        0.5625 * system_img_source_address[offsetY + 1][offsetX + 3] +
        0.125 * system_img_source_address[offsetY + 1][offsetX + 4];
    gray[4] =
        0.3125 * system_img_source_address[offsetY + 2][offsetX + 0] +
        0.5625 * system_img_source_address[offsetY + 2][offsetX + 1] +
        0.125 * system_img_source_address[offsetY + 2][offsetX + 2];
    gray[5] =
        0.3125 * system_img_source_address[offsetY + 2][offsetX + 2] +
        0.5625 * system_img_source_address[offsetY + 2][offsetX + 3] +
        0.125 * system_img_source_address[offsetY + 2][offsetX + 4];
    if (offsetX >= dimX - 2)
    {
        offsetY += 1;
        offsetX = 0;
    }
    if (offsetY >= dimY - 2)
    {
        offsetY = 0;
    }
    dimsOut[0] = dimX;
    dimsOut[1] = dimY;
    for (i = 0; i < 6; i++)
        write_token_non_blocking_double(gray_channel, gray[i]);
    write_token_non_blocking_uint16_t(offsetXOut_channel, offsetX);
    write_token_non_blocking_uint16_t(offsetYOut_channel, offsetY);
    for (i = 0; i < 2; i++)
        write_token_non_blocking_uint16_t(dimsOut_channel, dimsOut[i]);
}

void fire_getPx(
    double_channel_t *gray_channel,
    double_channel_t *copyX_channel,
    double_channel_t *copyY_channel)
{
    unsigned int i;
    double gray[6];
    double imgBlockX[6];
    double imgBlockY[6];
    // getPxImpl1
    //  edge [] from getPx port gray to getPxImpl1 port gray
    //  edge [] from getPxImpl1 port imgBlockX to getPx port copyX
    for (i = 0; i < 6; i++)
        read_token_non_blocking_double(gray_channel, &gray[i]);
    imgBlockX[0] = gray[0];
    imgBlockX[1] = gray[1];
    imgBlockX[2] = gray[2];
    imgBlockX[3] = gray[3];
    imgBlockX[4] = gray[4];
    imgBlockX[5] = gray[5];
    for (i = 0; i < 6; i++)
        write_token_non_blocking_double(copyX_channel, imgBlockX[i]);
    // getPxImpl2
    //  edge [] from getPx port gray to getPxImpl2 port gray
    //  edge [] from getPxImpl2 port imgBlockY to getPx port copyY
    imgBlockY[0] = gray[0];
    imgBlockY[1] = gray[1];
    imgBlockY[2] = gray[2];
    imgBlockY[3] = gray[3];
    imgBlockY[4] = gray[4];
    imgBlockY[5] = gray[5];
    for (i = 0; i < 6; i++)
        write_token_non_blocking_double(copyY_channel, imgBlockY[i]);
}

void fire_Gx(
    double_channel_t *imgBlockX_channel,
    double_channel_t *gx_channel)
{
    unsigned int i;
    double imgBlockX[6];
    double gx;
    for (i = 0; i < 6; i++)
        read_token_non_blocking_double(imgBlockX_channel, &imgBlockX[i]);
    gx = gx - imgBlockX[0];
    gx = gx + imgBlockX[1];
    gx = gx - 2.0 * imgBlockX[2];
    gx = gx + 2.0 * imgBlockX[3];
    gx = gx - imgBlockX[4];
    gx = gx + imgBlockX[5];
    write_token_non_blocking_double(gx_channel, gx);
}

void fire_Gy(
    double_channel_t *imgBlockY_channel,
    double_channel_t *gy_channel)
{
    unsigned int i;
    double imgBlockY[6];
    double gy;
    for (i = 0; i < 6; i++)
        read_token_non_blocking_double(imgBlockY_channel, &imgBlockY[i]);
    gy = gy + imgBlockY[0];
    gy = gy + 2.0 * imgBlockY[1];
    gy = gy + imgBlockY[2];
    gy = gy - imgBlockY[3];
    gy = gy - 2.0 * imgBlockY[4];
    gy = gy - imgBlockY[5];
    write_token_non_blocking_double(gy_channel, gy);
}

void fire_Abs(
    volatile double **system_img_sink,
    uint16_t_channel_t *dimsIn_channel,
    uint16_t_channel_t *offsetXIn_channel,
    uint16_t_channel_t *offsetYIn_channel,
    uint16_t_channel_t *offsetXOut_channel,
    uint16_t_channel_t *offsetYOut_channel,
    double_channel_t *gx_channel,
    double_channel_t *gy_channel)
{
    unsigned int i;
    volatile double **system_img_sink_address = system_img_sink;
    double gx;
    double gy;
    uint16_t offsetX;
    uint16_t offsetY;
    uint16_t dims[2];
    read_token_non_blocking_double(gx_channel, &gx);
    read_token_non_blocking_double(gy_channel, &gy);    
    read_token_non_blocking_uint16_t(offsetXIn_channel, &offsetX);
    read_token_non_blocking_uint16_t(offsetYIn_channel, &offsetY);
    for (i = 0; i < 6; i++)
        read_token_non_blocking_uint16_t(dimsIn_channel, &dims[i]);
    if (gx < 0.0)
        gx = -gx;
    if (gy < 0.0)
        gy = -gy;
    if (offsetX >= dims[0] - 2)
    {
        offsetY += 1;
        offsetX = 0;
    }
    if (offsetY >= dims[1] - 2)
    {
        offsetY = 0;
    }
    system_img_sink_address[offsetX][offsetY] = gx + gy;
    write_token_non_blocking_uint16_t(offsetXOut_channel, offsetX);
    write_token_non_blocking_uint16_t(offsetYOut_channel, offsetY);
}

#endif // LIBSDF_
