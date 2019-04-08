import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardingCardSorterTest {

    private final BoardingCardSorter cardSorter = new BoardingCardSorter();

    @Test
    void sortBoardingCards_UnorderedList_SortsBoardingCards() {
        BoardingCard card1 = BoardingCard.newBuilder()
                .locationFrom("Madrid")
                .locationTo("Stockholm")
                .build();

        BoardingCard card2 = BoardingCard.newBuilder()
                .locationFrom("Stockholm")
                .locationTo("London")
                .build();

        BoardingCard card3 = BoardingCard.newBuilder()
                .locationFrom("London")
                .locationTo("Paris")
                .build();

        List<BoardingCard> cards = asList(card2, card3, card1);

        List<BoardingCard> sortedCards = cardSorter.sortBoardingCards(cards);

        assertThat(sortedCards)
                .containsExactly(card1, card2, card3);
    }

    @Test
    void sortBoardingCards_MultipleFirstLocationCandidates_ThrowsIllegalStateException() {
        BoardingCard card1 = BoardingCard.newBuilder()
                .locationFrom("Madrid")
                .locationTo("Stockholm")
                .build();

        BoardingCard card2 = BoardingCard.newBuilder()
                .locationFrom("Stockholm")
                .locationTo("London")
                .build();

        BoardingCard card3 = BoardingCard.newBuilder()
                .locationFrom("London")
                .locationTo("Paris")
                .build();

        BoardingCard card4 = BoardingCard.newBuilder()
                .locationFrom("Gerona Airport")
                .locationTo("New York JFK")
                .build();

        List<BoardingCard> cards = asList(card2, card3, card1, card4);

        assertThatThrownBy(() -> cardSorter.sortBoardingCards(cards))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(
                        "Found multiple candidates for first location: [Gerona Airport, Madrid]");
    }

    @Test
    void sortBoardingCards_DuplicateLocationFrom_ThrowsIllegalStateException() {
        BoardingCard card1 = BoardingCard.newBuilder()
                .locationFrom("Madrid")
                .locationTo("Stockholm")
                .build();

        BoardingCard card2 = BoardingCard.newBuilder()
                .locationFrom("Stockholm")
                .locationTo("London")
                .build();

        BoardingCard card3 = BoardingCard.newBuilder()
                .locationFrom("London")
                .locationTo("Paris")
                .build();

        BoardingCard card4 = BoardingCard.newBuilder()
                .locationFrom("Madrid")
                .locationTo("New York JFK")
                .build();

        List<BoardingCard> cards = asList(card2, card3, card1, card4);

        assertThatThrownBy(() -> cardSorter.sortBoardingCards(cards))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Duplicate location from: Madrid");
    }

    @Test
    void sortBoardingCards_DuplicateLocationTo_ThrowsIllegalStateException() {
        BoardingCard card1 = BoardingCard.newBuilder()
                .locationFrom("Madrid")
                .locationTo("Stockholm")
                .build();

        BoardingCard card2 = BoardingCard.newBuilder()
                .locationFrom("Stockholm")
                .locationTo("London")
                .build();

        BoardingCard card3 = BoardingCard.newBuilder()
                .locationFrom("London")
                .locationTo("Paris")
                .build();

        BoardingCard card4 = BoardingCard.newBuilder()
                .locationFrom("Paris")
                .locationTo("London")
                .build();

        List<BoardingCard> cards = asList(card2, card3, card1, card4);

        assertThatThrownBy(() -> cardSorter.sortBoardingCards(cards))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Duplicate location to: London");
    }
}