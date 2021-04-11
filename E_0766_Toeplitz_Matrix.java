class E_0766_Toeplitz_Matrix {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        for (int cr = 0; cr < r - 1; cr++) {
            for (int cc = 0; cc < c - 1; cc++) {
                int val = matrix[cr][cc];
                int t = 1;
                while (cr + t < r && cc + t < c) {
                    if (matrix[cr+t][cc+t] != val) return false;
                    t++;
                }
            }
        }
        return true;
    }
}
