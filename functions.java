
import java.util.Scanner;

public class functions {

    public static void main(String args[]) {
        //MAIN APENAS PARA TESTE COM MATRIZES TRADICIONAIS E NAO MANIPULANDO AS IMAGENS.
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite um limiar: ");
        int m = sc.nextInt();
        int[][] matriz = new int[5][5];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = j;
            }

        }
        System.out.println("Matriz original:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.printf("%d", matriz[i][j]);
            }
            System.out.println("");
        }
        FiltroMedia(3, matriz);

        int[][] ma = new int[12][12];

        int[][] ma2 = new int[14][14];
        // ma2 = AddBordas(ma, 12,12);

    }

/*    
    public static Mat limiarizaçãoR(int m, Mat img) {
        Mat resultado = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);
        int tamanho = img.rows() * img.cols();
        int limiarUser = m;
        double[] limiar = new double[3];
        // Media das bandas;
        if (m == -1) { // Restrição caso nao seja escolhido pelo usuario.

            for (int i = 0; i < img.rows(); i++) {
                for (int j = 0; j < img.cols(); j++) {
                    double[] temp = img.get(i, j);
                    limiar[0] += temp[0];
                  //  limiar[1] += temp[1];
                  //  limiar[2] += temp[2];

                }
            }
            limiarUser = (int) ((limiar[0] / tamanho)); //Media das bandas.

        }
        // Limiarização
        for (int i = 0; i < img.rows(); i++) {
            for (int j = 0; j < img.cols(); j++) {
                double[] temp = img.get(i, j);
                double[] RGB = new double[3];

                if (temp[0] <= limiarUser) {
                    RGB[0] = 255;
                    RGB[1] = 255;
                    RGB[2] = 255;
                } else {
                    RGB[0] = 0;
                    RGB[1] = 0;
                    RGB[2] = 0;
                }
                resultado.put(i, j, RGB);
            }
        }

        return resultado;
    }
    */
    /*
       public static Mat limiarizaçãoG(int m, Mat img) {
        Mat resultado = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);
        int tamanho = img.rows() * img.cols();
        int limiarUser = m;
        double[] limiar = new double[3];
        // Media das bandas;
        if (m == -1) { // Restrição caso nao seja escolhido pelo usuario.

            for (int i = 0; i < img.rows(); i++) {
                for (int j = 0; j < img.cols(); j++) {
                    double[] temp = img.get(i, j);
                  //  limiar[0] += temp[0];
                      limiar[1] += temp[1];
                  //  limiar[2] += temp[2];

                }
            }
            limiarUser = (int) ((limiar[1] / tamanho)); //Media das bandas.

        }
        // Limiarização
        for (int i = 0; i < img.rows(); i++) {
            for (int j = 0; j < img.cols(); j++) {
                double[] temp = img.get(i, j);
                double[] RGB = new double[3];

                if (temp[1] <= limiarUser) {
                    RGB[0] = 255;
                    RGB[1] = 255;
                    RGB[2] = 255;
                } else {
                    RGB[0] = 0;
                    RGB[1] = 0;
                    RGB[2] = 0;
                }
                resultado.put(i, j, RGB);
            }
        }

        return resultado;
    }
    */
    /*
         public static Mat limiarizaçãoB(int m, Mat img) {
        Mat resultado = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);
        int tamanho = img.rows() * img.cols();
        int limiarUser = m;
        double[] limiar = new double[3];
        // Media das bandas;
        if (m == -1) { // Restrição caso nao seja escolhido pelo usuario.

            for (int i = 0; i < img.rows(); i++) {
                for (int j = 0; j < img.cols(); j++) {
                    double[] temp = img.get(i, j);
                  //  limiar[0] += temp[0];
                  //  limiar[1] += temp[1];
                    limiar[2] += temp[2];

                }
            }
            limiarUser = (int) ((limiar[2] / tamanho)); //Media das bandas.

        }
        // Limiarização
        for (int i = 0; i < img.rows(); i++) {
            for (int j = 0; j < img.cols(); j++) {
                double[] temp = img.get(i, j);
                double[] RGB = new double[3];

                if (temp[2] <= limiarUser) {
                    RGB[0] = 255;
                    RGB[1] = 255;
                    RGB[2] = 255;
                } else {
                    RGB[0] = 0;
                    RGB[1] = 0;
                    RGB[2] = 0;
                }
                resultado.put(i, j, RGB);
            }
        }

        return resultado;
    }
    */ 
    /*
    public static Mat FiltroMedia(int Tamk, Mat img) {
        int tamKernel = Tamk * Tamk; //Quantidade de elementos no Kernel;
        int nAddBordas = Tamk / 2; //Numero de adições de bordas variando com o kernel dado pelo usuario;
        System.out.println("Numero de repetições para colocar borda: " + nAddBordas);
        
        Mat resultado = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);// Resultado pos filtragem.
        Mat MBordas = img; // Matriz para manipulação com bordas.
        Mat kernel = new Mat(Tamk, Tamk, CvType.CV_8UC3); //Kernel.

        //Adicionando bordas.
        for (int a = 0; a < nAddBordas; a++) {
            MBordas = AddBordas(MBordas);
        }
        //Percorrendo os pixels da matriz original e realizando as medias.
        for (int i = 0; i < img.rows(); i++) {
            for (int j = 0; j < img.cols(); j++) {

                System.out.println("i: " + i + "j: " + j);// Posições do pixel atual.
                // Pegando o kernel.
                int aux = 0, aux2 = 0; //linha e coluna do kernel.            
                int v = j, k = i; // linha e coluna da minha matriz com bordas.                
                System.out.println("Começando com v: " + v + " e k: " + k);
                //Preenchendo o kernel.
                while (aux2 != Tamk) {
                    while (aux != Tamk) {
                        System.out.printf("Colocando no kernel[%d][%d] o meu MOriginal[%d][%d]\n", aux2, aux, k, v);
                        double[] temp = MBordas.get(k, v);
                        kernel.put(aux2, aux, temp);
                        aux++;
                        v++;
                    }
                    aux = 0;
                    v = j;
                    k++;
                    aux2++;
                }
                //Media do pixel.
                double[]total = new double[3];
                for (int a = 0; a < Tamk; a++) {
                    for (int b = 0; b < Tamk; b++) {
                        double[] temp = kernel.get(a, b);
                        total[0] += temp[0];//R
                        total[1] += temp[1];//G
                        total[2] += temp[2];//B

                    }
                }
                total[0] = total[0]/tamKernel;//media R
                total[1] = total[1]/tamKernel;// media G
                total[2] = total[2]/tamKernel;// media B       
                resultado.put(i,j,total); //insere no pixel atual a media.
                
            }

        }    
        return resultado;
    }
*/
    public static void FiltroMedia(int Tamk, int matrix[][]) {
        int tamKernel = Tamk * Tamk;
        int nAddBordas = Tamk / 2;
        System.out.println("Numero de repetições para colocar borda: " + nAddBordas);

        int linha = 5, coluna = 5, linhaMO = 5, colunaMO = 5;
        int[][] MOriginal = matrix;
        int[][] kernel = new int[Tamk][Tamk];

        //Adicionando bordas.
        for (int a = 0; a < nAddBordas; a++) {
            MOriginal = AddBordas2(MOriginal, linhaMO, colunaMO);
            linhaMO += 2;
            colunaMO += 2;
        }
        //Percorrendo os pixels da matriz original e realizando as medias.
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {

                //kernel[Tamk - 2][Tamk - 2] = matrix[i][j];
                System.out.println("M OrignAL: ");
                for (int k = 0; k < linhaMO; k++) {
                    for (int v = 0; v < colunaMO; v++) {
                        System.out.printf("%d ", MOriginal[k][v]);
                    }
                    System.out.println("");
                }
                System.out.println("i: " + i + "j: " + j);

                // Pegando o kernel.
                int aux = 0, aux2 = 0; //linha e coluna do kernel.            
                int v = j, k = i; // linha e coluna da minha matriz com bordas.                
                System.out.println("Começando com v: " + v + " e k: " + k);
                //Preenchendo o kernel.
                while (aux2 != Tamk) {
                    while (aux != Tamk) {
                        System.out.printf("Colocando no kernel[%d][%d] o meu MOriginal[%d][%d]\n", aux2, aux, k, v);

                        kernel[aux2][aux] = MOriginal[k][v];
                        aux++;
                        v++;
                    }
                    aux = 0;
                    v = j;
                    k++;
                    aux2++;
                }
                //Kernel do pixel atual.
                System.out.println("Kernel: ");
                for (int a = 0; a < Tamk; a++) {
                    for (int b = 0; b < Tamk; b++) {
                        System.out.printf("%d ", kernel[a][b]);
                    }
                    System.out.println("");
                }
                
                
                //Media do pixel.
                int total = 0;
                for (int a = 0; a < Tamk; a++) {
                    for (int b = 0; b < Tamk; b++) {
                        total += kernel[a][b];
                    }
                    System.out.println("");
                }
                int media = (int) (total / tamKernel);
                System.out.println("Media = " + media);
                matrix[i][j] = media; //Coloca a media no pixel atual;

            }

        }
        //Matriz pós media.
        System.out.println("Matriz pos media: ");
        for (int a = 0; a < linha; a++) {
            for (int b = 0; b < coluna; b++) {
                System.out.printf("%d ", matrix[a][b]);
            }
            System.out.println("");
        }

    }

    /*
    public static Mat AddBordas(Mat img) {

        Mat MBordas = new Mat(img.rows() + 2, img.cols() + 2, CvType.CV_8UC3);
        for (int i = 0; i < MBordas.rows(); i++) {
            for (int j = 0; j < MBordas.cols(); j++) {
                // System.out.printf("i:%d, j: %d\n", i, j);
                if (i == 0) {//borda superior.
                    if (j == 0) {//diagonal esquerda superior.
                        double[] temp = img.get(i, j);
                        MBordas.put(i, j, temp);
                    } else if (j == img.cols() + 1) {//diagonal direita superior
                        double[] temp = img.get(i, img.cols() - 1);
                        MBordas.put(i, j, temp);
                    } else {
                        //demais bordas
                        double[] temp = img.get(i, j - 1);
                        MBordas.put(i, j, temp);

                    }
                } else if ((j == 0) && (i != 0) && (i != img.rows() + 1)) {//borda lateral esquerda
                    double[] temp = img.get(i - 1, j);
                    MBordas.put(i, j, temp);

                } else if ((j == img.cols() + 1) && (i != 0) && (i != img.rows() + 1)) {//borda lateral direita
                    double[] temp = img.get(i - 1, img.cols() - 1);
                    MBordas.put(i, j, temp);

                } else if (i == img.rows() + 1) {// borda inferior
                    if (j == 0) {// diagonal inferior esquerda
                        double[] temp = img.get(img.rows() - 1, j);
                        MBordas.put(i, j, temp);

                    } else if (j == img.cols() + 1) { // diagonal inferior direita
                        double[] temp = img.get(img.rows() - 1, img.cols() - 1);
                        MBordas.put(i, j, temp);

                    } else { // demas bordas inferiores
                        double[] temp = img.get(img.rows() - 1, j - 1);
                        MBordas.put(i, j, temp);

                    }

                } else {
                    // preenchendo o meio da matriz.
                    double[] temp = img.get(i - 1, j - 1);
                    MBordas.put(i, j, temp);
                }

            }
        }
        return MBordas;
    }
     */
    public static int[][] AddBordas2(int matrix[][], int linhas, int colunas) {
        int[][] MBordas = new int[linhas + 2][colunas + 2];

        for (int i = 0; i < linhas + 2; i++) {
            for (int j = 0; j < colunas + 2; j++) {
                //  System.out.printf("i:%d, j: %d\n", i, j);
                if (i == 0) {
                    if (j == 0) {
                        MBordas[i][j] = matrix[i][j];
                    } else if (j == colunas + 1) {
                        MBordas[i][j] = matrix[i][colunas - 1];
                    } else {
                        //  System.out.println("entrei");
                        MBordas[i][j] = matrix[i][j - 1];
                        //  System.out.println("normal");
                    }
                } else if ((j == 0) && (i != 0) && (i != linhas + 1)) {
                    MBordas[i][j] = matrix[i - 1][j];
                } else if ((j == colunas + 1) && (i != 0) && (i != linhas + 1)) {
                    MBordas[i][j] = matrix[i - 1][colunas - 1];
                } else if (i == linhas + 1) {
                    if (j == 0) {
                        MBordas[i][j] = matrix[linhas - 1][j];
                    } else if (j == colunas + 1) {
                        MBordas[i][j] = matrix[linhas - 1][colunas - 1];
                    } else {
                        MBordas[i][j] = matrix[linhas - 1][j - 1];
                    }

                } else {
                    MBordas[i][j] = matrix[i - 1][j - 1];
                }

            }
        }
        return MBordas;
    }

}
