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

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.NumberList;

public class BoardSet extends SimpleSet<Board>
{
	protected Class<?> getTypClass() {
		return Board.class;
	}

   public BoardSet()
   {
      // empty
   }

   public BoardSet(Board... objects)
   {
      for (Board obj : objects)
      {
         this.add(obj);
      }
   }

   public BoardSet(Collection<Board> objects)
   {
      this.addAll(objects);
   }

   public static final BoardSet EMPTY_SET = new BoardSet().withFlag(BoardSet.READONLY);


   public BoardPO createBoardPO()
   {
      return new BoardPO(this.toArray(new Board[this.size()]));
   }


   public String getEntryType()
   {
      return "swe443.assignment5.mancala.Board";
   }


   @Override
   public BoardSet getNewList(boolean keyValue)
   {
      return new BoardSet();
   }


   public BoardSet filter(Condition<Board> condition) {
      BoardSet filterList = new BoardSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public BoardSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof Collection)
      {
         this.addAll((Collection<Board>)value);
      }
      else if (value != null)
      {
         this.add((Board) value);
      }
      
      return this;
   }
   
   public BoardSet without(Board value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public BoardSet setUpBoard()
   {
      return BoardSet.EMPTY_SET;
   }

   /**
    * Loop through the current set of Board objects and collect a set of the House objects reached via houses. 
    * 
    * @return Set of House objects reachable via houses
    */
   public HouseSet getHouses()
   {
      HouseSet result = new HouseSet();
      
      for (Board obj : this)
      {
         result.with(obj.getHouses());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Board objects and collect all contained objects with reference houses pointing to the object passed as parameter. 
    * 
    * @param value The object required as houses neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via houses
    */
   public BoardSet filterHouses(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      BoardSet answer = new BoardSet();
      
      for (Board obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getHouses()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Board object passed as parameter to the Houses attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Houses attributes.
    */
   public BoardSet withHouses(House value)
   {
      for (Board obj : this)
      {
         obj.withHouses(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Board object passed as parameter from the Houses attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public BoardSet withoutHouses(House value)
   {
      for (Board obj : this)
      {
         obj.withoutHouses(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Board objects and collect a set of the Store objects reached via stores. 
    * 
    * @return Set of Store objects reachable via stores
    */
   public StoreSet getStores()
   {
      StoreSet result = new StoreSet();
      
      for (Board obj : this)
      {
         result.with(obj.getStores());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Board objects and collect all contained objects with reference stores pointing to the object passed as parameter. 
    * 
    * @param value The object required as stores neighbor of the collected results. 
    * 
    * @return Set of Store objects referring to value via stores
    */
   public BoardSet filterStores(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      BoardSet answer = new BoardSet();
      
      for (Board obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getStores()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Board object passed as parameter to the Stores attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Stores attributes.
    */
   public BoardSet withStores(Store value)
   {
      for (Board obj : this)
      {
         obj.withStores(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Board object passed as parameter from the Stores attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public BoardSet withoutStores(Store value)
   {
      for (Board obj : this)
      {
         obj.withoutStores(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Board objects and collect a set of the Player objects reached via player. 
    * 
    * @return Set of Player objects reachable via player
    */
   public PlayerSet getPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Board obj : this)
      {
         result.with(obj.getPlayer());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Board objects and collect all contained objects with reference player pointing to the object passed as parameter. 
    * 
    * @param value The object required as player neighbor of the collected results. 
    * 
    * @return Set of Player objects referring to value via player
    */
   public BoardSet filterPlayer(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      BoardSet answer = new BoardSet();
      
      for (Board obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPlayer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Board object passed as parameter to the Player attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Player attributes.
    */
   public BoardSet withPlayer(Player value)
   {
      for (Board obj : this)
      {
         obj.withPlayer(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Board object passed as parameter from the Player attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public BoardSet withoutPlayer(Player value)
   {
      for (Board obj : this)
      {
         obj.withoutPlayer(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Board objects and collect a list of the turn attribute values. 
    * 
    * @return List of boolean objects reachable via turn attribute
    */
   public BooleanList getTurn()
   {
      BooleanList result = new BooleanList();
      
      for (Board obj : this)
      {
         result.add(obj.isTurn());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Board objects and collect those Board objects where the turn attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Board objects that match the parameter
    */
   public BoardSet filterTurn(boolean value)
   {
      BoardSet result = new BoardSet();
      
      for (Board obj : this)
      {
         if (value == obj.isTurn())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Board objects and assign value to the turn attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Board objects now with new attribute values.
    */
   public BoardSet withTurn(boolean value)
   {
      for (Board obj : this)
      {
         obj.setTurn(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public BooleanList isGameOver()
   {
      
      BooleanList result = new BooleanList();
      
      for (Board obj : this)
      {
         result.add( obj.isGameOver() );
      }
      return result;
   }

   
   //==========================================================================
   
   public BoardSet checkGameStatus()
   {
      return BoardSet.EMPTY_SET;
   }

   
   //==========================================================================
   
   public PlayerSet getPlayerTurn()
   {
      
      PlayerSet result = new PlayerSet();
      
      for (Board obj : this)
      {
         result.add( obj.getPlayerTurn() );
      }
      return result;
   }

   
   //==========================================================================
   
   public BoardSet printBoard()
   {
      return BoardSet.EMPTY_SET;
   }

   
   //==========================================================================
   
   public NumberList checkWinner()
   {
      
      NumberList result = new NumberList();
      
      for (Board obj : this)
      {
         result.add( obj.checkWinner() );
      }
      return result;
   }

}
