package com.edu.BSU.inbetween.common;

import com.BSU.inbetween.R;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class CardMapper extends Activity
{
	public static Drawable getCardImage(Card card, Resources res)
	{
		Drawable image = null;
		
		if(card.getSuit() == Suit.DIAMONDS)
		{
			switch(card.getValue())
			{
				case 1:
					image = res.getDrawable(R.drawable.card_ace_of_diamonds);
					break;
				case 2:
					image = res.getDrawable(R.drawable.card_two_of_diamonds);
					break;
				case 3:
					image = res.getDrawable(R.drawable.card_three_of_diamonds);
					break;
				case 4:
					image =res.getDrawable(R.drawable.card_four_of_diamonds);
					break;
				case 5:
					image = res.getDrawable(R.drawable.card_five_of_diamonds);
					break;
				case 6:
					image = res.getDrawable(R.drawable.card_six_of_diamonds);
					break;
				case 7:
					image = res.getDrawable(R.drawable.card_seven_of_diamonds);
					break;
				case 8:
					image = res.getDrawable(R.drawable.card_eight_of_diamonds);
					break;
				case 9:
					image = res.getDrawable(R.drawable.card_nine_of_diamonds);
					break;
				case 10:
					image = res.getDrawable(R.drawable.card_ten_of_diamonds);
					break;
				case 11:
					image = res.getDrawable(R.drawable.card_jack_of_diamonds);
					break;
				case 12:
					image = res.getDrawable(R.drawable.card_queen_of_diamonds);
					break;
				case 13:
					image = res.getDrawable(R.drawable.card_king_of_diamonds);
					break;
				}
			}
			else if(card.getSuit() == Suit.SPADES)
			{
				switch(card.getValue())
				{
					case 1:
						image = res.getDrawable(R.drawable.card_ace_of_spades);
						break;
					case 2:
						image = res.getDrawable(R.drawable.card_two_of_spades);
						break;
					case 3:
						image = res.getDrawable(R.drawable.card_three_of_spades);
						break;
					case 4:
						image =res.getDrawable(R.drawable.card_four_of_spades);
						break;
					case 5:
						image = res.getDrawable(R.drawable.card_five_of_spades);
						break;
					case 6:
						image = res.getDrawable(R.drawable.card_six_of_spades);
						break;
					case 7:
						image = res.getDrawable(R.drawable.card_seven_of_spades);
						break;
					case 8:
						image = res.getDrawable(R.drawable.card_eight_of_spades);
						break;
					case 9:
						image = res.getDrawable(R.drawable.card_nine_of_spades);
						break;
					case 10:
						image = res.getDrawable(R.drawable.card_ten_of_spades);
						break;
					case 11:
						image = res.getDrawable(R.drawable.card_jack_of_spades);
						break;
					case 12:
						image = res.getDrawable(R.drawable.card_queen_of_spades);
						break;
					case 13:
						image = res.getDrawable(R.drawable.card_king_of_spades);
						break;
				}
			}
			else if(card.getSuit() == Suit.CLUBS)
			{
				switch(card.getValue())
				{
					case 1:
						image = res.getDrawable(R.drawable.card_ace_of_clubs);
						break;
					case 2:
						image = res.getDrawable(R.drawable.card_two_of_clubs);
						break;
					case 3:
						image = res.getDrawable(R.drawable.card_three_of_clubs);
						break;
					case 4:
						image =res.getDrawable(R.drawable.card_four_of_clubs);
						break;
					case 5:
						image = res.getDrawable(R.drawable.card_five_of_clubs);
						break;
					case 6:
						image = res.getDrawable(R.drawable.card_six_of_clubs);
						break;
					case 7:
						image = res.getDrawable(R.drawable.card_seven_of_clubs);
						break;
					case 8:
						image = res.getDrawable(R.drawable.card_eight_of_clubs);
						break;
					case 9:
						image = res.getDrawable(R.drawable.card_nine_of_clubs);
						break;
					case 10:
						image = res.getDrawable(R.drawable.card_ten_of_clubs);
						break;
					case 11:
						image = res.getDrawable(R.drawable.card_jack_of_clubs);
						break;
					case 12:
						image = res.getDrawable(R.drawable.card_queen_of_clubs);
						break;
					case 13:
						image = res.getDrawable(R.drawable.card_king_of_clubs);
						break;
				}
			}
			else if(card.getSuit()==Suit.HEARTS)
			{
				switch(card.getValue())
				{
					case 1:
						image = res.getDrawable(R.drawable.card_ace_of_hearts);
						break;
					case 2:
						image = res.getDrawable(R.drawable.card_two_of_hearts);
						break;
					case 3:
						image = res.getDrawable(R.drawable.card_three_of_hearts);
						break;
					case 4:
						image =res.getDrawable(R.drawable.card_four_of_hearts);
						break;
					case 5:
						image = res.getDrawable(R.drawable.card_five_of_hearts);
						break;
					case 6:
						image = res.getDrawable(R.drawable.card_six_of_hearts);
						break;
					case 7:
						image = res.getDrawable(R.drawable.card_seven_of_hearts);
						break;
					case 8:
						image = res.getDrawable(R.drawable.card_eight_of_hearts);
						break;
					case 9:
						image = res.getDrawable(R.drawable.card_nine_of_hearts);
						break;
					case 10:
						image = res.getDrawable(R.drawable.card_ten_of_hearts);
						break;
					case 11:
						image = res.getDrawable(R.drawable.card_jack_of_hearts);
						break;
					case 12:
						image = res.getDrawable(R.drawable.card_queen_of_hearts);
						break;
					case 13:
						image = res.getDrawable(R.drawable.card_king_of_hearts);
						break;
				}
			}
			else
				image = res.getDrawable(R.drawable.card_back);
		
		return image;
		}
	}