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

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
   /**
    * 
    * @see <a href='../../../../../../src/main/java/Model.java'>Model.java</a>
 */
   public  class House implements SendableEntity
{

   
   //==========================================================================
   public void lastSownEvent(  )
   {
         this.getBoard().setTurn(!this.getBoard().isTurn());
         System.out.println("Player gets to play again");
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
   
   
   public void removeYou()
   {
      setBoard(null);
      setLeftSide(null);
      setRightSide(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_STONES = "stones";
   
   private int stones;

   public int getStones()
   {
      return this.stones;
   }
   
   public void setStones(int value)
   {
      if (this.stones != value) {
      
         int oldValue = this.stones;
         this.stones = value;
         this.firePropertyChange(PROPERTY_STONES, oldValue, value);
      }
   }
   
   public House withStones(int value)
   {
      setStones(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getStones());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       one
    * House ----------------------------------- Board
    *              houses                   board
    * </pre>
    */
   
   public static final String PROPERTY_BOARD = "board";

   private Board board = null;

   public Board getBoard()
   {
      return this.board;
   }

   public boolean setBoard(Board value)
   {
      boolean changed = false;
      
      if (this.board != value)
      {
         Board oldValue = this.board;
         
         if (this.board != null)
         {
            this.board = null;
            oldValue.withoutHouses(this);
         }
         
         this.board = value;
         
         if (value != null)
         {
            value.withHouses(this);
         }
         
         firePropertyChange(PROPERTY_BOARD, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public House withBoard(Board value)
   {
      setBoard(value);
      return this;
   } 

   public Board createBoard()
   {
      Board value = new Board();
      withBoard(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * House ----------------------------------- House
    *              rightSide                   leftSide
    * </pre>
    */
   
   public static final String PROPERTY_LEFTSIDE = "leftSide";

   private House leftSide = null;

   public House getLeftSide()
   {
      return this.leftSide;
   }

   public boolean setLeftSide(House value)
   {
      boolean changed = false;
      
      if (this.leftSide != value)
      {
         House oldValue = this.leftSide;
         
         if (this.leftSide != null)
         {
            this.leftSide = null;
            oldValue.setRightSide(null);
         }
         
         this.leftSide = value;
         
         if (value != null)
         {
            value.withRightSide(this);
         }
         
         firePropertyChange(PROPERTY_LEFTSIDE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public House withLeftSide(House value)
   {
      setLeftSide(value);
      return this;
   } 

   public House createLeftSide()
   {
      House value = new House();
      withLeftSide(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * House ----------------------------------- House
    *              leftSide                   rightSide
    * </pre>
    */
   
   public static final String PROPERTY_RIGHTSIDE = "rightSide";

   private House rightSide = null;

   public House getRightSide()
   {
      return this.rightSide;
   }

   public boolean setRightSide(House value)
   {
      boolean changed = false;
      
      if (this.rightSide != value)
      {
         House oldValue = this.rightSide;
         
         if (this.rightSide != null)
         {
            this.rightSide = null;
            oldValue.setLeftSide(null);
         }
         
         this.rightSide = value;
         
         if (value != null)
         {
            value.withLeftSide(this);
         }
         
         firePropertyChange(PROPERTY_RIGHTSIDE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public House withRightSide(House value)
   {
      setRightSide(value);
      return this;
   } 

   public House createRightSide()
   {
      House value = new House();
      withRightSide(value);
      return value;
   } 
}
