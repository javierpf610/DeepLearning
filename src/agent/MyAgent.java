package agent;
import NeuralNetwork.NeuralNetwork;
import jade.core.behaviours.OneShotBehaviour;

import java.util.List;


public class MyAgent extends jade.core.Agent {
    private AgentGUI myGUI;
    protected void setup() {
        myGUI=new AgentGUI(this);
        myGUI.showGui();
    }

    public void executeAgent(final String entrada) {
        addBehaviour(new OneShotBehaviour() {
            public void action() {

                double [][] X= {
                        {0,0,1,0,0,0,1,1,0,0,1,1,1,1,1,0,1,1,0,1,0,0,1,0,1},//IZQUIERDA
                        {0,0,1,0,0,0,1,1,0,0,1,1,1,1,1,0,1,1,0,1,0,0,1,0,1},//IZQUIERDA
                        {0,0,1,0,0,0,1,0,0,0,1,1,1,1,1,0,1,0,0,1,0,0,1,0,1},//IZQUIERDA
                        {0,1,1,0,0,1,1,0,0,0,1,1,1,1,1,1,1,0,0,1,0,1,1,0,1},//IZQUIERDA
                        {0,0,1,0,0,0,0,1,1,0,1,1,1,1,1,1,0,1,1,0,1,0,1,0,0},//DERECHA
                        {0,0,1,0,0,0,0,0,1,0,1,1,1,1,1,1,0,0,1,0,1,0,1,0,0},//DERECHA
                        {0,0,1,1,0,0,0,0,1,1,1,1,1,1,1,1,0,0,1,1,1,0,1,1,0}//DERECHA

                };
                double [][] Y= {
                        {0,0},{0,0},{0,0},{0,0},{1,1},{1,1},{1,1}
                };

                NeuralNetwork nn = new NeuralNetwork(25,12,2);
                List<Double> output;
                nn.cycleTraining(X, Y, 100000);
               /* double [][] input = {

                        {0,0,1,1,0,0,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,0},//DERECHA
                        {1,1,0,0,0,1,1,1,0,0,1,1,1,1,1,1,1,1,0,0,1,1,0,0,0}//IZQUIERDA

                };*/

                String [] userRawValues;

                userRawValues = entrada.split(",");

                double [][] input = new double [1][userRawValues.length];

                for (int i = 0; i < userRawValues.length; i++) {
                    input[0][i] = Double.parseDouble(userRawValues[i]);
                }
                for(double[] d :input)
                {
                    output = nn.prediccion(d);
                    if(output.get(0)+output.get(1) >1){
                        System.out.println("Señal a la derecha");
                    }
                    else{
                        System.out.println("Señal a la izquierda");
                    }
                    System.out.printf("Valor:"+output.toString());
                    System.out.println("");


                }


            }
        } );

    }


}
