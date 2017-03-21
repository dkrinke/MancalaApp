package com.example.daniel.mancalaapp;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;

public class HousePO extends PatternObject<HousePO, House>
{

    public HouseSet allMatches()
   {
      this.setDoAllMatches(true);
      
      HouseSet matches = new HouseSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add(this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public HousePO(){
      newInstance(null);
   }

   public HousePO(House... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public HousePO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void lastSownEvent()
   {
      if (this.getPattern().getHasMatch())
      {
          getCurrentMatch().lastSownEvent();
      }
   }

   public HousePO createStonesCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(House.PROPERTY_STONES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public HousePO createStonesCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(House.PROPERTY_STONES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public HousePO createStonesAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(House.PROPERTY_STONES)
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
   
   public HousePO withStones(int value)
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
      super.hasLink(House.PROPERTY_BOARD, result);
      
      return result;
   }

   public BoardPO createBoardPO(String modifier)
   {
      BoardPO result = new BoardPO(new Board[]{});
      
      result.setModifier(modifier);
      super.hasLink(House.PROPERTY_BOARD, result);
      
      return result;
   }

   public HousePO createBoardLink(BoardPO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_BOARD);
   }

   public HousePO createBoardLink(BoardPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_BOARD, modifier);
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
      super.hasLink(House.PROPERTY_LEFTSIDE, result);
      
      return result;
   }

   public HousePO createLeftSidePO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(House.PROPERTY_LEFTSIDE, result);
      
      return result;
   }

   public HousePO createLeftSideLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_LEFTSIDE);
   }

   public HousePO createLeftSideLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_LEFTSIDE, modifier);
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
      super.hasLink(House.PROPERTY_RIGHTSIDE, result);
      
      return result;
   }

   public HousePO createRightSidePO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(House.PROPERTY_RIGHTSIDE, result);
      
      return result;
   }

   public HousePO createRightSideLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_RIGHTSIDE);
   }

   public HousePO createRightSideLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_RIGHTSIDE, modifier);
   }

   public House getRightSide()
   {
      if (this.getPattern().getHasMatch())
      {
         return this.getCurrentMatch().getRightSide();
      }
      return null;
   }

}
