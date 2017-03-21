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
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;

public class StoreSet extends SimpleSet<Store>
{
	protected Class<?> getTypClass() {
		return Store.class;
	}

   public StoreSet()
   {
      // empty
   }

   public StoreSet(Store... objects)
   {
      for (Store obj : objects)
      {
         this.add(obj);
      }
   }

   public StoreSet(Collection<Store> objects)
   {
      this.addAll(objects);
   }

   public static final StoreSet EMPTY_SET = new StoreSet().withFlag(StoreSet.READONLY);


   public StorePO createStorePO()
   {
      return new StorePO(this.toArray(new Store[this.size()]));
   }


   public String getEntryType()
   {
      return "swe443.assignment5.mancala.Store";
   }


   @Override
   public StoreSet getNewList(boolean keyValue)
   {
      return new StoreSet();
   }


   public StoreSet filter(Condition<Store> condition) {
      StoreSet filterList = new StoreSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public StoreSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof Collection)
      {
         this.addAll((Collection<Store>)value);
      }
      else if (value != null)
      {
         this.add((Store) value);
      }
      
      return this;
   }
   
   public StoreSet without(Store value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public NumberList takeOppositePebbles()
   {
      
      NumberList result = new NumberList();
      
      for (Store obj : this)
      {
         result.add( obj.takeOppositePebbles() );
      }
      return result;
   }

   
   //==========================================================================
   
   public StoreSet lastSownEvent()
   {
      return StoreSet.EMPTY_SET;
   }


   /**
    * Loop through the current set of Store objects and collect a list of the stones attribute values. 
    * 
    * @return List of int objects reachable via stones attribute
    */
   public NumberList getStones()
   {
      NumberList result = new NumberList();
      
      for (Store obj : this)
      {
         result.add(obj.getStones());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Store objects and collect those Store objects where the stones attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Store objects that match the parameter
    */
   public StoreSet filterStones(int value)
   {
      StoreSet result = new StoreSet();
      
      for (Store obj : this)
      {
         if (value == obj.getStones())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Store objects and collect those Store objects where the stones attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Store objects that match the parameter
    */
   public StoreSet filterStones(int lower, int upper)
   {
      StoreSet result = new StoreSet();
      
      for (Store obj : this)
      {
         if (lower <= obj.getStones() && obj.getStones() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Store objects and assign value to the stones attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Store objects now with new attribute values.
    */
   public StoreSet withStones(int value)
   {
      for (Store obj : this)
      {
         obj.setStones(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Store objects and collect a set of the Board objects reached via board. 
    * 
    * @return Set of Board objects reachable via board
    */
   public BoardSet getBoard()
   {
      BoardSet result = new BoardSet();
      
      for (Store obj : this)
      {
         result.with(obj.getBoard());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Store objects and collect all contained objects with reference board pointing to the object passed as parameter. 
    * 
    * @param value The object required as board neighbor of the collected results. 
    * 
    * @return Set of Board objects referring to value via board
    */
   public StoreSet filterBoard(Object value)
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
      
      StoreSet answer = new StoreSet();
      
      for (Store obj : this)
      {
         if (neighbors.contains(obj.getBoard()) || (neighbors.isEmpty() && obj.getBoard() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Store object passed as parameter to the Board attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Board attributes.
    */
   public StoreSet withBoard(Board value)
   {
      for (Store obj : this)
      {
         obj.withBoard(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Store objects and collect a set of the House objects reached via leftSide. 
    * 
    * @return Set of House objects reachable via leftSide
    */
   public HouseSet getLeftSide()
   {
      HouseSet result = new HouseSet();
      
      for (Store obj : this)
      {
         result.with(obj.getLeftSide());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Store objects and collect all contained objects with reference leftSide pointing to the object passed as parameter. 
    * 
    * @param value The object required as leftSide neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via leftSide
    */
   public StoreSet filterLeftSide(Object value)
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
      
      StoreSet answer = new StoreSet();
      
      for (Store obj : this)
      {
         if (neighbors.contains(obj.getLeftSide()) || (neighbors.isEmpty() && obj.getLeftSide() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow leftSide reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of House objects reachable via leftSide transitively (including the start set)
    */
   public HouseSet getLeftSideTransitive()
   {
      HouseSet todo = new HouseSet().with(this);
      
      HouseSet result = new HouseSet();
      
      while ( ! todo.isEmpty())
      {
         House current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getLeftSide()))
            {
               todo.with(current.getLeftSide());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Store object passed as parameter to the LeftSide attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their LeftSide attributes.
    */
   public StoreSet withLeftSide(House value)
   {
      for (Store obj : this)
      {
         obj.withLeftSide(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Store objects and collect a set of the House objects reached via rightSide. 
    * 
    * @return Set of House objects reachable via rightSide
    */
   public HouseSet getRightSide()
   {
      HouseSet result = new HouseSet();
      
      for (Store obj : this)
      {
         result.with(obj.getRightSide());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Store objects and collect all contained objects with reference rightSide pointing to the object passed as parameter. 
    * 
    * @param value The object required as rightSide neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via rightSide
    */
   public StoreSet filterRightSide(Object value)
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
      
      StoreSet answer = new StoreSet();
      
      for (Store obj : this)
      {
         if (neighbors.contains(obj.getRightSide()) || (neighbors.isEmpty() && obj.getRightSide() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow rightSide reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of House objects reachable via rightSide transitively (including the start set)
    */
   public HouseSet getRightSideTransitive()
   {
      HouseSet todo = new HouseSet().with(this);
      
      HouseSet result = new HouseSet();
      
      while ( ! todo.isEmpty())
      {
         House current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getRightSide()))
            {
               todo.with(current.getRightSide());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Store object passed as parameter to the RightSide attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their RightSide attributes.
    */
   public StoreSet withRightSide(House value)
   {
      for (Store obj : this)
      {
         obj.withRightSide(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Store objects and collect a set of the Store objects reached via opposite. 
    * 
    * @return Set of Store objects reachable via opposite
    */
   public StoreSet getOpposite()
   {
      StoreSet result = new StoreSet();
      
      for (Store obj : this)
      {
         result.with(obj.getOpposite());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Store objects and collect all contained objects with reference opposite pointing to the object passed as parameter. 
    * 
    * @param value The object required as opposite neighbor of the collected results. 
    * 
    * @return Set of Store objects referring to value via opposite
    */
   public StoreSet filterOpposite(Object value)
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
      
      StoreSet answer = new StoreSet();
      
      for (Store obj : this)
      {
         if (neighbors.contains(obj.getOpposite()) || (neighbors.isEmpty() && obj.getOpposite() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow opposite reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Store objects reachable via opposite transitively (including the start set)
    */
   public StoreSet getOppositeTransitive()
   {
      StoreSet todo = new StoreSet().with(this);
      
      StoreSet result = new StoreSet();
      
      while ( ! todo.isEmpty())
      {
         Store current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getOpposite()))
            {
               todo.with(current.getOpposite());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Store object passed as parameter to the Opposite attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Opposite attributes.
    */
   public StoreSet withOpposite(Store value)
   {
      for (Store obj : this)
      {
         obj.withOpposite(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public StoreSet lastSownEvent(int p0)
   {
      return StoreSet.EMPTY_SET;
   }

}
