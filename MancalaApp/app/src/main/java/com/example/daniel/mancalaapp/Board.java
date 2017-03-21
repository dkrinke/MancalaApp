/*
   Copyright (c) 2017 Wave
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.example.daniel.mancalaapp;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @see <a href='../../../../../../src/main/java/Model.java'>Model.java</a>
 */
public  class Board implements SendableEntity
{
    public static void main(String[] arg){
        Scanner sc = new Scanner(System.in);
        int gameState = 0;
        System.out.println("I want to play a game.....");
        while(gameState != -1){
            System.out.println("Lets play mancala\n How many players should we have? (type -1 to quit)");
            String s = sc.next();
            try{
                gameState = Integer.parseInt(s);
            }
            catch(Exception e){
                System.out.println("Please enter a whole number");
                continue;
            }
            if(gameState == -1)break;
            else if(gameState == 0){
                System.out.println("Really? You can't play with 0 players");
                continue;
            }
            else if(gameState == 1){
                System.out.println("Sorry you haven't purchased the CPU DLC try a different number");
                continue;
            }
            else if(gameState > 2){
                System.out.println("Eeehhhh, that is a pain to set up. Lets do a smaller number");
                continue;
            }
            else if(gameState < 0){
                System.out.println("Please enter a whole number");
                continue;
            }
            Board b = new Board();
            System.out.println("What is player 1's name?");
            s = sc.next();
            Player player1 = b.createPlayer().withName(s).withBoard(b).withMyTurn(true);
            System.out.println("what is player 2's name?");
            s = sc.next();
            Player player2 = b.createPlayer().withName(s).withBoard(b).withMyTurn(false);
            b.setUpBoard();
            while(!b.isGameOver()){
                Player p = null;
                if(b.isTurn())p = player1;
                else p = player2;
                boolean validMove = false;
                while(!validMove) {
                    b.printBoard();
                    System.out.println("It is " + p.getName() + "'s turn, pick a store to sow (1 through 12)");
                    int move = -1;
                    try {
                        s = sc.next();
                        move = Integer.parseInt(s);
                    }
                    catch(Exception e){
                        System.out.println("Invalid move");
                        continue;
                    }
                    validMove = b.makeMove(p,move-1);
                    if(!validMove)
                        System.out.println("Invalid move");
                }
            }
            String playAgain = "";
            while(!(playAgain.equals("yes") || playAgain.equals("no"))) {
                System.out.println("Do you want to play again? (yes/no)");
                playAgain = sc.next();
                if (playAgain.equals("yes"))
                    gameState = 0;
                else if (playAgain.equals("no"))
                    gameState = -1;
                else
                    System.out.println("Type 'yes' or 'no'");
            }
        }
        System.out.println("Thank you for playing");
    }
    //==========================================================================
    public void setUpBoard(  ) {
        House house1 = createHouses().withBoard(this);
        House house2 = createHouses().withBoard(this);


        ArrayList<Store> storeSide1 = new ArrayList<Store>();
        Store previous = null;
        for (int i = 0; i < 6; i++) {
            Store store1 = createStores().withBoard(this);
            store1.withStones(3);
            if(previous == null){
                store1.setLeftSide(house2);
                previous = store1;
            }
            else{
                store1.setLeftSide(previous);
                previous = store1;
            }
            storeSide1.add(store1);
        }
        previous.setRightSide(house1);
        previous = null;
        for (int i = 0; i < 6; i++) {
            //Store store2 = storeSide1.get(storeSide1.size()-1-i).createOpposite().withBoard(this).withOpposite(storeSide1.get(i));
            //store2.setOpposite(storeSide1.get(storeSide1.size()-1-i));

            Store store2 = createStores().withBoard(this);
            store2.withStones(3);
            storeSide1.get(storeSide1.size()-1-i).setOpposite(store2);
            if(previous == null){
                store2.setLeftSide(house1);
                previous = store2;
            }
            else{
                store2.setLeftSide(previous);
                previous = store2;
            }
        }
        previous.setRightSide(house2);
        this.setTurn(true);
    }


    //==========================================================================

    protected PropertyChangeSupport listeners = null;

    public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
    {
        if (listeners != null) {
            listeners.firePropertyChange(propertyName, oldValue, newValue);
            return true;
        }
        return false;
    }

    public boolean addPropertyChangeListener(PropertyChangeListener listener)
    {
        if (listeners == null) {
            listeners = new PropertyChangeSupport(this);
        }
        listeners.addPropertyChangeListener(listener);
        return true;
    }

    public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if (listeners == null) {
            listeners = new PropertyChangeSupport(this);
        }
        listeners.addPropertyChangeListener(propertyName, listener);
        return true;
    }

    public boolean removePropertyChangeListener(PropertyChangeListener listener) {
        if (listeners == null) {
            listeners.removePropertyChangeListener(listener);
        }
        listeners.removePropertyChangeListener(listener);
        return true;
    }

    public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
        if (listeners != null) {
            listeners.removePropertyChangeListener(propertyName, listener);
        }
        return true;
    }


    //==========================================================================


    /********************************************************************
     * <pre>
     *              one                       many
     * Board ----------------------------------- House
     *              board                   houses
     * </pre>
     */

    public static final String PROPERTY_HOUSES = "houses";

    private HouseSet houses = null;

    public HouseSet getHouses()
    {
        if (this.houses == null)
        {
            return HouseSet.EMPTY_SET;
        }

        return this.houses;
    }

    public Board withHouses(House... value)
    {
        if(value==null){
            return this;
        }
        for (House item : value)
        {
            if (item != null)
            {
                if (this.houses == null)
                {
                    this.houses = new HouseSet();
                }

                boolean changed = this.houses.add (item);

                if (changed)
                {
                    item.withBoard(this);
                    firePropertyChange(PROPERTY_HOUSES, null, item);
                }
            }
        }
        return this;
    }

    public Board withoutHouses(House... value)
    {
        for (House item : value)
        {
            if ((this.houses != null) && (item != null))
            {
                if (this.houses.remove(item))
                {
                    item.setBoard(null);
                    firePropertyChange(PROPERTY_HOUSES, item, null);
                }
            }
        }
        return this;
    }

    public House createHouses()
    {
        House value = new House();
        withHouses(value);
        return value;
    }

    public Store createHousesStore()
    {
        Store value = new Store();
        withHouses(value);
        return value;
    }


    /********************************************************************
     * <pre>
     *              one                       many
     * Board ----------------------------------- Store
     *              board                   stores
     * </pre>
     */

    public static final String PROPERTY_STORES = "stores";

    private StoreSet stores = null;

    public StoreSet getStores()
    {
        if (this.stores == null)
        {
            return StoreSet.EMPTY_SET;
        }

        return this.stores;
    }

    public Board withStores(Store... value)
    {
        if(value==null){
            return this;
        }
        for (Store item : value)
        {
            if (item != null)
            {
                if (this.stores == null)
                {
                    this.stores = new StoreSet();
                }

                boolean changed = this.stores.add (item);

                if (changed)
                {
                    item.withBoard(this);
                    firePropertyChange(PROPERTY_STORES, null, item);
                }
            }
        }
        return this;
    }

    public Board withoutStores(Store... value)
    {
        for (Store item : value)
        {
            if ((this.stores != null) && (item != null))
            {
                if (this.stores.remove(item))
                {
                    item.setBoard(null);
                    firePropertyChange(PROPERTY_STORES, item, null);
                }
            }
        }
        return this;
    }

    public Store createStores()
    {
        Store value = new Store();
        withStores(value);
        return value;
    }


    /********************************************************************
     * <pre>
     *              one                       many
     * Board ----------------------------------- Player
     *              board                   player
     * </pre>
     */

    public static final String PROPERTY_PLAYER = "player";

    private PlayerSet player = null;

    public PlayerSet getPlayer()
    {
        if (this.player == null)
        {
            return PlayerSet.EMPTY_SET;
        }

        return this.player;
    }

    public Board withPlayer(Player... value)
    {
        if(value==null){
            return this;
        }
        for (Player item : value)
        {
            if (item != null)
            {
                if (this.player == null)
                {
                    this.player = new PlayerSet();
                }

                boolean changed = this.player.add (item);

                if (changed)
                {
                    item.withBoard(this);
                    firePropertyChange(PROPERTY_PLAYER, null, item);
                }
            }
        }
        return this;
    }

    public Board withoutPlayer(Player... value)
    {
        for (Player item : value)
        {
            if ((this.player != null) && (item != null))
            {
                if (this.player.remove(item))
                {
                    item.setBoard(null);
                    firePropertyChange(PROPERTY_PLAYER, item, null);
                }
            }
        }
        return this;
    }

    public Player createPlayer()
    {
        Player value = new Player();
        withPlayer(value);
        return value;
    }

    // Below is for testing purposes
    public void checkOpposites()
    {
        for(int i = 0; i < getStores().size(); i++)
        {
            System.out.println(getStores().get(i) + " --> " + getStores().get(i).getOpposite());
        }
    }

    public void printPlayerList()
    {
        System.out.println(getPlayer());
    }

    public void printBoard()
    {
        StoreSet s = getStores();
        HouseSet h = getHouses();
        for(int x = s.size()-1; x >= s.size()/2;x--)
            System.out.print(" "+ s.get(x));
        System.out.println();
        System.out.println(h.get(1)+"           "+h.get(0));
        for(int x = 0; x < s.size()/2;x++)
            System.out.print(" "+ s.get(x));
        System.out.println();
        System.out.println();
    }



    public boolean makeMove(Player player, int i) {
        if(isGameOver())                                               // If the game is over
            return false;                                               // Don't so any moves
        if (player.isMyTurn() != this.turn)                                   // If a player is making a move out of turn
            return false;                                               // Ignore him, he is a jerk
        if (this.turn && ((i >= this.stores.size() / 2) || (i < 0)))              // If player one is making an invalid move
            return false;                                               // Ignore him
        else if (!this.turn && ((i >= this.stores.size()) || (i < this.stores.size()/2))) // If player two is making an invalid move
            return false;                                               // Ignore him
        House pickedStore = this.stores.get(i);                                                // Get the chosen store
        int stoneCount = pickedStore.getStones();                                              // Get the stones from the store
        if(stoneCount == 0) return false;                                                       // If the store is empty this is an invalid move
        pickedStore.setStones(0);                                                               // Set the stones in store to 0
        int finalDestination = (i + stoneCount);                                                // Calculate the place the final stone will go
        if (!this.turn)
            finalDestination++;                                                           // If its player 2's turn account for player 1's house
        finalDestination = finalDestination % (this.houses.size() + this.stores.size());             // Simplify it to a place on the board
        for (int x = 0; x < stoneCount; x++) {                                            // for every stone in the store
            pickedStore = pickedStore.getRightSide();                                           // Get move right
            pickedStore.setStones(pickedStore.getStones() + 1);                                  // Add a stone to the house/store
        }                                                                                       // When we are done adding stones
         if ((finalDestination == this.stores.size() / 2 && this.turn) ||
                (finalDestination == this.stores.size() + 1 && !this.turn)) {                            // Check to see if the final destination is the player's house
            pickedStore.lastSownEvent();                                                        // If it is player gets another turn
        }
        else if ((pickedStore.getStones() == 1) && (finalDestination != this.stores.size() / 2) &&      // Else if the we sowed the last stone in an empty store
                (finalDestination != this.stores.size() + 1)) {
            ((Store) pickedStore).lastSownEvent(finalDestination);                               //  We may be able to steal the opposite pit's stones
        }

        this.setTurn(!this.turn);                                                               //  Update Turn
        if(isGameOver())                                                                            // If the game is over
            checkGameStatus();                                                                      // Determine the winner and stuff
        return true;
    }

    public int checkWinner()
    {
        System.out.println(this.player.get(0).getName()+" has "+this.getHouses().get(0).getStones());
        System.out.println(this.player.get(1).getName()+" has "+this.getHouses().get(1).getStones());
        if(this.getHouses().get(0).getStones() > this.getHouses().get(1).getStones())
        {
            return 1;
        }
        else if (this.getHouses().get(0).getStones() < this.getHouses().get(1).getStones())
        {
            return 2;

        }
        else{
            return 0;
        }
    }

    public void setUpCustomBoard(int[] stores, int[] homes) {
        this.setTurn(true);
        House house1 = createHouses().withBoard(this).withStones(homes[0]);
        House house2 = createHouses().withBoard(this).withStones(homes[1]);

        ArrayList<Store> storeSide1 = new ArrayList<Store>();
        Store previous = null;
        for (int i = 0; i < 6; i++) {
            Store store1 = createStores().withBoard(this);
            store1.withStones(stores[i]);
            if(previous == null){
                store1.setLeftSide(house2);
                previous = store1;
            }
            else{
                store1.setLeftSide(previous);
                previous = store1;
            }
            storeSide1.add(store1);
        }
        previous.setRightSide(house1);
        previous = null;
        for (int i = 0; i < 6; i++) {
            //Store store2 = storeSide1.get(storeSide1.size()-1-i).createOpposite().withBoard(this).withOpposite(storeSide1.get(i));
            //store2.setOpposite(storeSide1.get(storeSide1.size()-1-i));

            Store store2 = createStores().withBoard(this);
            store2.withStones(stores[i+6]);
            storeSide1.get(storeSide1.size()-1-i).setOpposite(store2);
            if(previous == null){
                store2.setLeftSide(house1);
                previous = store2;
            }
            else{
                store2.setLeftSide(previous);
                previous = store2;
            }
        }
        previous.setRightSide(house2);
    }

   
   //==========================================================================
   
   public static final String PROPERTY_TURN = "turn";
   
   private boolean turn;

   public boolean isTurn()
   {
      return this.turn;
   }
   
   public void setTurn(boolean value)
   {
      if (this.turn != value) {
      
         boolean oldValue = this.turn;
         this.turn = value;
         this.firePropertyChange(PROPERTY_TURN, oldValue, value);
      }
   }
   
   public Board withTurn(boolean value)
   {
      setTurn(value);
      return this;
   } 

   
   //==========================================================================
   public boolean isGameOver(  )
   {
       System.out.println("Checking if Game is over");
       if(this.turn){
           for(int x = 0; x < this.stores.size()/2; x++)
               if(this.stores.get(x).getStones() != 0)
                   return false;
       }
       else {
           for(int x = this.stores.size() / 2; x < this.stores.size(); x++)
               if (this.stores.get(x).getStones() != 0)
                   return false;
       }
       return true;
   }

   
   //==========================================================================
   public void checkGameStatus(  )
   {
       System.out.println("\nGame Over!");

       int winner = checkWinner();
       String result = "Game Over!";

       if(!(winner == 0))
       {

           switch (winner)
           {
               case 1:
                   result = "You Win!";
                   break;
               case 2:
                   result = "You Lose!";
                   break;
           }
           MainActivity.playerTurn.setText(this.player.get(winner-1).getName() + " Wins!");
           System.out.println(this.player.get(winner-1).getName() + " Wins!\n");
       }
       else
       {
           result = "Tie Game!";
           MainActivity.playerTurn.setText("Tie Game!");
           System.out.println("Tie Game!\n");
       }
       System.out.println("Resulting board:");
       printBoard();

       Intent gameOver = new Intent(MainActivity.activity, GameOver.class);
       gameOver.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       gameOver.putExtra("Result", result);
       MainActivity.connected = false;
       MainActivity.activity.startActivity(gameOver);
       MainActivity.activity.finish();
   }

   
   //==========================================================================
   public Player getPlayerTurn(  )
   {
      for(int x =0; x < this.player.size();x++)
          if(player.get(x).isMyTurn() == this.turn)
              return player.get(x);
      return null;
   }
}
