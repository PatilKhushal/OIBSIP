import java.util.Scanner;

public class Task2 
{
    final static int MAX = 100;
    static boolean play()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Guess number from 1 to " + Task2.MAX);    
        int computer = (int) Math.round(Math.random() * 100);
        /* System.out.println(computer); */
        boolean flag = false;
        for(int i = 1; i < 11; i++)
        {
            System.out.print("Enter your guess (1 to " + Task2.MAX + ") :\t");
            int guess = sc.nextInt();
            if(guess == computer)
            {
                flag = true;
                System.out.println("\nYou guessed it right in " + i + " chances!!\nYour Score (outof 5):\t" + ((10 - (i - 1)) / 2) + "\n\n");
                break;
            }
            else if(guess > computer)
                System.out.println("Guess a little low!!");
            else
                System.out.println("Guess a little high!!");
        }

        if(!flag)
            System.out.println("\nYou have lost the game!!\nYour Score (outof 5) :\t0\n\n");
        
        return flag;
    }
    public static void main(String[] args) 
    {
        int choice = 0;
        System.out.println("*********** WELCOME TO GUESS GAME ***********");
        do 
        {
            System.out.print("\n1.Start Game 2.Exit\nEnter your choice :\t");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            if(choice != 1)
                break;
            else
            {
                while (play()) 
                {
                    System.out.print("1.Next Round 2.Exit\nEnter your choice :\t");    
                    choice = sc.nextInt();

                    if(choice != 1)
                        break;

                }
            }
        } while (choice == 1);
    }    
}