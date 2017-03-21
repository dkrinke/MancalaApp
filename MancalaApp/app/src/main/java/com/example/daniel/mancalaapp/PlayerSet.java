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
import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.ObjectSet;

public class PlayerSet extends SimpleSet<Player>
{
	protected Class<?> getTypClass() {
		return Player.class;
	}

   public PlayerSet()
   {
      // empty
   }

   public PlayerSet(Player... objects)
   {
      for (Player obj : objects)
      {
         this.add(obj);
      }
   }

   public PlayerSet(Collection<Player> objects)
   {
      this.addAll(objects);
   }

   public static final PlayerSet EMPTY_SET = new PlayerSet().withFlag(PlayerSet.READONLY);


   public PlayerPO createPlayerPO()
   {
      return new PlayerPO(this.toArray(new Player[this.size()]));
   }


   public String getEntryType()
   {
      return "swe443.assignment5.mancala.Player";
   }


   @Override
   public PlayerSet getNewList(boolean keyValue)
   {
      return new PlayerSet();
   }


   public PlayerSet filter(Condition<Player> condition) {
      PlayerSet filterList = new PlayerSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public PlayerSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof Collection)
      {
         this.addAll((Collection<Player>)value);
      }
      else if (value != null)
      {
         this.add((Player) value);
      }
      
      return this;
   }
   
   public PlayerSet without(Player value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   



   /**
    * Loop through the current set of Player objects and collect a list of the myTurn attribute values. 
    * 
    * @return List of boolean objects reachable via myTurn attribute
    */
   public BooleanList getMyTurn()
   {
      BooleanList result = new BooleanList();
      
      for (Player obj : this)
      {
         result.add(obj.isMyTurn());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the myTurn attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterMyTurn(boolean value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.isMyTurn())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and assign value to the myTurn attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Player objects now with new attribute values.
    */
   public PlayerSet withMyTurn(boolean value)
   {
      for (Player obj : this)
      {
         obj.setMyTurn(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Player objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Player obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterName(String value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterName(String lower, String upper)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Player objects now with new attribute values.
    */
   public PlayerSet withName(String value)
   {
      for (Player obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Player objects and collect a set of the Board objects reached via board. 
    * 
    * @return Set of Board objects reachable via board
    */
   public BoardSet getBoard()
   {
      BoardSet result = new BoardSet();
      
      for (Player obj : this)
      {
         result.with(obj.getBoard());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Player objects and collect all contained objects with reference board pointing to the object passed as parameter. 
    * 
    * @param value The object required as board neighbor of the collected results. 
    * 
    * @return Set of Board objects referring to value via board
    */
   public PlayerSet filterBoard(Object value)
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
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getBoard()) || (neighbors.isEmpty() && obj.getBoard() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Player object passed as parameter to the Board attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Board attributes.
    */
   public PlayerSet withBoard(Board value)
   {
      for (Player obj : this)
      {
         obj.withBoard(value);
      }
      
      return this;
   }

}
