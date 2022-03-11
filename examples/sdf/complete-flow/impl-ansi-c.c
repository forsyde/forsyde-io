#include <math.h>

#define SYSTEM_PARAM_DIM_X 3
#define SYSTEM_PARAM_DIM_Y 3

inline double abs(double a) {
    return a > 0 ? a : -a;
}

typedef struct {
    unsigned int offsetX;
    unsigned int offsetY;
} GetPxState_t;

typedef struct {
    double[3][3] imgBlock;
    GetPxState_t newState;
} GetPxOut_t;

typedef struct {
    unsigned int offsetX;
    unsigned int offsetY;
} NormState_t;

typedef struct {
    double norm;
    NormState_t newState;
} NormOut_t;


double toAsciiArt(char[3] rgb) {
    return rgb[0] * 0.3125 + rgb[1] * 0.5625 + rgb[2] * 0.125;
}

GetPxOut_t GetPx(double** img, GetPxState_t state) {
    GetPxOut_t result;
    result.imgBlock[0][0] = img[state.offsetX + 0][state.offsetY + 0];
    result.imgBlock[0][1] = img[state.offsetX + 0][state.offsetY + 1];
    result.imgBlock[0][2] = img[state.offsetX + 0][state.offsetY + 2];
    result.imgBlock[1][0] = img[state.offsetX + 1][state.offsetY + 0];
    result.imgBlock[1][1] = img[state.offsetX + 1][state.offsetY + 1];
    result.imgBlock[1][2] = img[state.offsetX + 1][state.offsetY + 2];
    result.imgBlock[2][0] = img[state.offsetX + 2][state.offsetY + 0];
    result.imgBlock[2][1] = img[state.offsetX + 2][state.offsetY + 1];
    result.imgBlock[2][2] = img[state.offsetX + 2][state.offsetY + 2];
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

double Gx(double[3][3] matrixBlock) {
    double result = 0.0;
    result = result - matrixBlock[0][0];
    result = result + matrixBlock[0][2];
    result = result - 2.0*matrixBlock[1][0];
    result = result + 2.0*matrixBlock[1][2];
    result = result - matrixBlock[2][0];
    result = result + matrixBlock[2][2];
    return result;
}

double Gy(double[3][3] matrixBlock) {
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
    return sqrt(gx*gx + gy*gy);
}

NormOut_t NormApprox(double gx, double gy, NormState_t state) {
    NormOut_t result;
    result.norm = abs(gx) + abs(gy);
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