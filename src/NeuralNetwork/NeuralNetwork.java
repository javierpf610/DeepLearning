package NeuralNetwork;

import helperMatrix.Matrix;

import java.util.List;

public class NeuralNetwork {

    Matrix weights_inputToHidden, weights_hiddenToOutput, bias_hidden, bias_output;
    double alphaRate =0.01;

    public NeuralNetwork(int input_size,int hidden_size,int output_size) {
        //Weights matrix for Input to Hidden
        //Primer parametro, de donde viene segundo parametro a donde va.
        weights_inputToHidden = new Matrix(hidden_size,input_size);
        //Weights matrix for Hidden to Output
        weights_hiddenToOutput = new Matrix(output_size,hidden_size);
        //Bias for hidden
        bias_hidden = new Matrix(hidden_size,1);
        //Bias for output
        bias_output = new Matrix(output_size,1);

    }

    //Toma la capa de neuronas de input y las convierte en un objeto de tipo matrix
    public List<Double> prediccion(double[] user_input)
    {
        //Convierte de arreglo a objeto para input
        Matrix input = Matrix.convertir_Array_Matrix(user_input);
        //Inicializa la capa hidden con las conexiones de input a hidden multiplicandola por los valores de input
        // (valores de activacion de la primera capa)
        Matrix hidden = Matrix.multiplicacion(weights_inputToHidden, input);
        //Le suma al resultado de la multiplicacion los valores del bias de la capa oculta
        //(En la primer corrida, tienen valores aleatorios)
        hidden.suma(bias_hidden);
        //Obtenemos los valores de activación para hidden a traves de la sigmoide
        hidden.sigmoide();
        //Inicializa la capa output con las conexiones de hidden a output multiplicandola por los valores de hidden
        // (valores de activacion de la segunda capa)
        Matrix output = Matrix.multiplicacion(weights_hiddenToOutput,hidden);
        //Le suma al resultado de la multiplicacion los valores del bias de la capa output
        output.suma(bias_output);
        //Obtenemos los valores de activación para output a traves de la sigmoide
        output.sigmoide();

        return output.convertir_Matrix_Array();
    }

    public void neuralNetworkTraining(double [] sample_input_x, double [] sample_target_y)
    {
        Matrix input = Matrix.convertir_Array_Matrix(sample_input_x);
        Matrix hidden = Matrix.multiplicacion(weights_inputToHidden, input);
        hidden.suma(bias_hidden);
        hidden.sigmoide();

        Matrix output = Matrix.multiplicacion(weights_hiddenToOutput,hidden);
        output.suma(bias_output);
        output.sigmoide();

        Matrix target = Matrix.convertir_Array_Matrix(sample_target_y);

        Matrix error = Matrix.resta_m(target, output);
        Matrix gradient = output.derivada_sigmoide();
        gradient.multiplicacion(error);
        gradient.multiplicacion(alphaRate);

        Matrix hidden_T = Matrix.transpuesta(hidden);
        Matrix who_delta =  Matrix.multiplicacion(gradient, hidden_T);

        weights_hiddenToOutput.suma(who_delta);
        bias_output.suma(gradient);

        Matrix who_T = Matrix.transpuesta(weights_hiddenToOutput);
        Matrix hidden_errors = Matrix.multiplicacion(who_T, error);

        Matrix h_gradient = hidden.derivada_sigmoide();
        h_gradient.multiplicacion(hidden_errors);
        h_gradient.multiplicacion(alphaRate);

        Matrix i_T = Matrix.transpuesta(input);
        Matrix wih_delta = Matrix.multiplicacion(h_gradient, i_T);

        weights_inputToHidden.suma(wih_delta);
        bias_hidden.suma(h_gradient);

    }

    public void cycleTraining(double[][]dataset_x, double[][]dataset_y, int iterations)
    {
        for(int i=0;i<iterations;i++)
        {
            int sampleN =  (int)(Math.random() * dataset_x.length );
            this.neuralNetworkTraining(dataset_x[sampleN], dataset_y[sampleN]);
        }
    }

}
