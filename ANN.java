package ann;

/**
 *
 * @author samy
 */

class Node{
    float Weight;
    
    Node(){}
    Node(float w){
        Weight = w;
        
    }
}
class NeuralNet{
    Node[] Nodes;
    int InputSize;
    float LearningRate;
    
    
    NeuralNet(){}
    
    NeuralNet(int Size,float weights[],float LR){
        InputSize=Size;
        LearningRate=LR;
        
        Nodes = new Node[Size];
        for(int i=0;i<Size;i++)
            Nodes[i] = new Node(weights[i]);
    }
    
    void fit(int input[], int output){
        float functionInput =0.0f;                                               //input to Function f(a).Here a = sum of (inputs*weights)
        
        for(int i=0;i<InputSize;i++){
            functionInput += Nodes[i].Weight * (float)input[i];
        }
        float out = 1/(1 + (float)Math.exp(-functionInput));
        
        for(int i=0;i<InputSize;i++){
            Nodes[i].Weight +=  LearningRate*input[i]*(output-out)*out*(1-out);
        }
        
    }
    
    float predict(int input[]){
        float functionInput=0.0f; 
        
        for(int i=0;i<InputSize;i++){
            functionInput += Nodes[i].Weight * (float)input[i];
        }
        return 1/(1 + (float)Math.exp(-functionInput));
    }
}

public class ANN {

    public static void main(String[] args) {

        System.out.println("****Artificial Neural Network (Single Node)****");
        int[][] train_input = {{0,1,1},{1,0,0},{1,1,1},{0,1,1}};
        int[] train_output = {0 , 1 , 1 , 0};
        int size = 3;
        int Repeatations = 100000;
        float[] weights = {0f , 0f , 0f};
        NeuralNet NN = new NeuralNet(size,weights,0.0001f);
        
        for(int j=0;j<Repeatations;j++){
            for(int i=0;i<size;i++){
                NN.fit(train_input[i], train_output[i]);
            }
        }
        
        int[] test_input = {1 , 0 , 1}; 
        System.out.println("Predicted Output is " + NN.predict(test_input));
        
    }
    
}
