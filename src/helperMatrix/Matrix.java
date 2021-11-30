package helperMatrix;

import java.util.ArrayList;
import java.util.List;

public class Matrix
{
    int filas, columnas;
    double [][]data;

    //Constructor de la clase matriz
    public Matrix(int filas, int columnas)
    {
        data= new double[filas][columnas];
        this.filas = filas;
        this.columnas = columnas;
        for(int i = 0; i< filas; i++)
        {
            for(int j = 0; j< columnas; j++)
            {
                //Se inicializa la matriz con valores aleatorios en -1 y 1
                //data[i][j]=Math.random()*2-1;
                //o no
                data[i][j]=0;
            }
        }
    }
    //Suma un escalar a cada uno de los elementos
    public void suma(double scaler)
    {
        for(int i = 0; i< filas; i++)
        {
            for(int j = 0; j< columnas; j++)
            {
                this.data[i][j]+=scaler;
            }

        }
    }
    //Suma básica de matrices
    public void suma(Matrix sumando)
    {
        if(columnas !=sumando.columnas || filas !=sumando.filas) {
            System.out.println("Matrices incompatibles");
            return;
        }

        for(int i = 0; i< filas; i++)
        {
            for(int j = 0; j< columnas; j++)
            {
                this.data[i][j]+=sumando.data[i][j];
            }
        }
    }

    //Resta entre matrices
    public static Matrix resta_m(Matrix a, Matrix b) {
        if(a.columnas !=b.columnas || a.filas !=b.filas) {
            System.out.println("Matrices incompatibles");
            return null;
        }

        Matrix ans=new Matrix(a.filas,a.columnas);

        for(int i = 0; i<a.filas; i++)
        {
            for(int j = 0; j<a.columnas; j++)
            {
                ans.data[i][j]=a.data[i][j]-b.data[i][j];
            }
        }
        return ans;
    }

    //Transpuesta de una matriz
    public static Matrix transpuesta(Matrix a) {

        Matrix ans=new Matrix(a.columnas,a.filas);

        for(int i = 0; i<a.filas; i++)
        {
            for(int j = 0; j<a.columnas; j++)
            {
                ans.data[j][i]=a.data[i][j];
            }
        }
        return ans;
    }

    //Multiplicacion de dos matrices, retorna un objeto matriz nuevo
    public static Matrix multiplicacion(Matrix a, Matrix b) {

        Matrix ans=new Matrix(a.filas,b.columnas);

        for(int i = 0; i<ans.filas; i++)
        {
            for(int j = 0; j<ans.columnas; j++)
            {
                double sum=0;
                for(int k = 0; k<a.columnas; k++)
                {
                    sum+=a.data[i][k]*b.data[k][j];
                }
                ans.data[i][j]=sum;
            }
        }
        return ans;
    }

    //Multiplicacion de matriz instancia con matriz recibida
    public void multiplicacion(Matrix a) {
        for(int i = 0; i<a.filas; i++)
        {
            for(int j = 0; j<a.columnas; j++)
            {
                this.data[i][j]*=a.data[i][j];
            }
        }

    }

    //Multiplicación de matriz instancia con escalar
    public void multiplicacion(double a) {
        for(int i = 0; i< filas; i++)
        {
            for(int j = 0; j< columnas; j++)
            {
                this.data[i][j]*=a;
            }
        }

    }

    //Función de activación sigmoide
    public void sigmoide() {
        for(int i = 0; i< filas; i++)
        {
            for(int j = 0; j< columnas; j++)
                this.data[i][j] = 1/(1+Math.exp(-this.data[i][j]));
        }

    }

    //Función CostFuntion a partir de la función sigmoide
    public Matrix derivada_sigmoide() {

        Matrix ans=new Matrix(filas, columnas);

        for(int i = 0; i< filas; i++)
        {
            for(int j = 0; j< columnas; j++)
                ans.data[i][j] = this.data[i][j] * (1-this.data[i][j]);
        }
        return ans;

    }


    //Toma los valores de x y los convierte en un objeto de tipo matriz, de dimensión 1
    public static Matrix convertir_Array_Matrix(double[]x)
    {
        Matrix ans = new Matrix(x.length,1);

        for(int i =0;i<x.length;i++)
            ans.data[i][0]=x[i];
        return ans;

    }
    //Convierte un objeto de tipo Matrix a un list común
    public List<Double> convertir_Matrix_Array() {

        List<Double> ans= new ArrayList<Double>()  ;

        for(int i = 0; i< filas; i++)
        {
            for(int j = 0; j< columnas; j++)
            {
                ans.add(data[i][j]);
            }
        }
        return ans;
    }

}