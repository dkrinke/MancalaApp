package com.example.daniel.mancalaapp;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;

public class StorePO extends PatternObject<StorePO, Store>
{

    public StoreSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StoreSet matches = new StoreSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add(this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public StorePO(){
      newInstance(null);
   }

   public StorePO(Store... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public StorePO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public int takeOppositePebbles()
   {
      if (this.getPattern().getHasMatch())
      {
         return getCurrentMatch().takeOppositePebbles();
      }
      return 0;
   }

   
   //==========================================================================
   
   public void lastSownEvent()
   {
      if (this.getPattern().getHasMatch())
      {
          getCurrentMatch().lastSownEvent();
      }
   }

   public StorePO createStonesCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Store.PROPERTY_STONES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StorePO createStonesCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Store.PROPERTY_STONES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StorePO createStonesAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Store.PROPERTY_STONES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getStones()
   {
      if (this.getPattern().getHasMatch())
      {
         return getCurrentMatch().getStones();
      }
      return 0;
   }
   
   public StorePO withStones(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         getCurrentMatch().setStones(value);
      }
      return this;
   }
   
   public BoardPO createBoardPO()
   {
      BoardPO result = new BoardPO(new Board[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Store.PROPERTY_BOARD, result);
      
      return result;
   }

   public BoardPO createBoardPO(String modifier)
   {
      BoardPO result = new BoardPO(new Board[]{});
      
      result.setModifier(modifier);
      super.hasLink(Store.PROPERTY_BOARD, result);
      
      return result;
   }

   public StorePO createBoardLink(BoardPO tgt)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_BOARD);
   }

   public StorePO createBoardLink(BoardPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_BOARD, modifier);
   }

   public Board getBoard()
   {
      if (this.getPattern().getHasMatch())
      {
         return this.getCurrentMatch().getBoard();
      }
      return null;
   }

   public HousePO createLeftSidePO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Store.PROPERTY_LEFTSIDE, result);
      
      return result;
   }

   public HousePO createLeftSidePO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(Store.PROPERTY_LEFTSIDE, result);
      
      return result;
   }

   public StorePO createLeftSideLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_LEFTSIDE);
   }

   public StorePO createLeftSideLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_LEFTSIDE, modifier);
   }

   public House getLeftSide()
   {
      if (this.getPattern().getHasMatch())
      {
         return this.getCurrentMatch().getLeftSide();
      }
      return null;
   }

   public HousePO createRightSidePO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Store.PROPERTY_RIGHTSIDE, result);
      
      return result;
   }

   public HousePO createRightSidePO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(Store.PROPERTY_RIGHTSIDE, result);
      
      return result;
   }

   public StorePO createRightSideLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_RIGHTSIDE);
   }

   public StorePO createRightSideLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_RIGHTSIDE, modifier);
   }

   public House getRightSide()
   {
      if (this.getPattern().getHasMatch())
      {
         return this.getCurrentMatch().getRightSide();
      }
      return null;
   }

   public StorePO createOppositePO()
   {
      StorePO result = new StorePO(new Store[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Store.PROPERTY_OPPOSITE, result);
      
      return result;
   }

   public StorePO createOppositePO(String modifier)
   {
      StorePO result = new StorePO(new Store[]{});
      
      result.setModifier(modifier);
      super.hasLink(Store.PROPERTY_OPPOSITE, result);
      
      return result;
   }

   public StorePO createOppositeLink(StorePO tgt)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_OPPOSITE);
   }

   public StorePO createOppositeLink(StorePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_OPPOSITE, modifier);
   }

   public Store getOpposite()
   {
      if (this.getPattern().getHasMatch())
      {
         return this.getCurrentMatch().getOpposite();
      }
      return null;
   }

   
   //==========================================================================
   


   
   //==========================================================================
   
   public void lastSownEvent(int current)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Store) getCurrentMatch()).lastSownEvent(current);
      }
   }

}
