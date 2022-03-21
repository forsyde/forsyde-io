#include <math.h>

const unsigned int system_param_dim_x = 3;
const unsigned int system_param_dim_y = 3;
const volatile double** system_img_source_address = 0x00000;
volatile double** system_img_sink_address = 0x00000;

#define SYSTEM_PARAM_DIM_X 3
#define SYSTEM_PARAM_DIM_Y 3

double custom_abs(double a) {
    return a > 0 ? a : -a;
}

typedef struct {
    unsigned int offsetX;
    unsigned int offsetY;
} GetPxState_t;

typedef struct {
    double imgBlockX[3][3];
    double imgBlockY[3][3];
    GetPxState_t newState;
} GetPxOut_t;

typedef struct {
    unsigned int offsetX;
    unsigned int offsetY;
} NormState_t;

typedef struct {
    NormState_t newState;
} NormOut_t;


double toAsciiArt(char rgb[3]) {
    return rgb[0] * 0.3125 + rgb[1] * 0.5625 + rgb[2] * 0.125;
}

GetPxOut_t GetPx(GetPxState_t state) {
    GetPxOut_t result;
    result.imgBlockX[0][0] = system_img_source_address[state.offsetX + 0][state.offsetY + 0];
    result.imgBlockX[0][1] = system_img_source_address[state.offsetX + 0][state.offsetY + 1];
    result.imgBlockX[0][2] = system_img_source_address[state.offsetX + 0][state.offsetY + 2];
    result.imgBlockX[1][0] = system_img_source_address[state.offsetX + 1][state.offsetY + 0];
    result.imgBlockX[1][1] = system_img_source_address[state.offsetX + 1][state.offsetY + 1];
    result.imgBlockX[1][2] = system_img_source_address[state.offsetX + 1][state.offsetY + 2];
    result.imgBlockX[2][0] = system_img_source_address[state.offsetX + 2][state.offsetY + 0];
    result.imgBlockX[2][1] = system_img_source_address[state.offsetX + 2][state.offsetY + 1];
    result.imgBlockX[2][2] = system_img_source_address[state.offsetX + 2][state.offsetY + 2];
    result.imgBlockY[0][0] = result.imgBlockX[0][0];
    result.imgBlockY[0][1] = result.imgBlockX[0][1];
    result.imgBlockY[0][2] = result.imgBlockX[0][2];
    result.imgBlockY[1][0] = result.imgBlockX[1][0];
    result.imgBlockY[1][1] = result.imgBlockX[1][1];
    result.imgBlockY[1][2] = result.imgBlockX[1][2];
    result.imgBlockY[2][0] = result.imgBlockX[2][0];
    result.imgBlockY[2][1] = result.imgBlockX[2][1];
    result.imgBlockY[2][2] = result.imgBlockX[2][2];
    if (state.offsetX >= SYSTEM_PARAM_DIM_X - 2) {
         state.offsetY += 1;
         state.offsetX = 0;
    }
    if (state.offsetY >= SYSTEM_PARAM_DIM_Y - 2) {
        state.offsetY = 0;
    }
    result.newState = state;
    return result;
}

double Gx(double matrixBlock[3][3]) {
    double result = 0.0;
    result = result - matrixBlock[0][0];
    result = result + matrixBlock[0][2];
    result = result - 2.0*matrixBlock[1][0];
    result = result + 2.0*matrixBlock[1][2];
    result = result - matrixBlock[2][0];
    result = result + matrixBlock[2][2];
    return result;
}

double Gy(double matrixBlock[3][3]) {
    double result = 0.0;
    result = result - matrixBlock[0][0];
    result = result + matrixBlock[2][0];
    result = result - 2.0*matrixBlock[0][1];
    result = result + 2.0*matrixBlock[2][1];
    result = result - matrixBlock[0][2];
    result = result + matrixBlock[2][2];
    return result;
}

NormOut_t Norm(double gx, double gy, NormState_t state) {
    NormOut_t result;
    double norm = sqrt(gx*gx + gy*gy);
    if (state.offsetX >= SYSTEM_PARAM_DIM_X - 2) {
         state.offsetY += 1;
         state.offsetX = 0;
    }
    if (state.offsetY >= SYSTEM_PARAM_DIM_Y - 2) {
        state.offsetY = 0;
    }
    result.newState = state;
    return result;
}

NormOut_t NormApprox(double gx, double gy, NormState_t state) {
    NormOut_t result;
    system_img_sink_address[state.offsetX][state.offsetY] = custom_abs(gx) + custom_abs(gy);
    if (state.offsetX >= SYSTEM_PARAM_DIM_X - 2) {
         state.offsetY += 1;
         state.offsetX = 0;
    }
    if (state.offsetY >= SYSTEM_PARAM_DIM_Y - 2) {
        state.offsetY = 0;
    }
    result.newState = state;
    return result;
}

// here just so a standard GCC call does not complain.
int main() {
    return 0;
}
