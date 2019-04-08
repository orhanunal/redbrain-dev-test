import java.util.List;

import static java.util.Arrays.asList;

public class Application {

    public static void main(String[] args) {

        BoardingCard card1 = BoardingCard.newBuilder()
                .locationFrom("Madrid")
                .locationTo("Barcelona")
                .transportationType(TransportationType.TRAIN)
                .seat("45B")
                .transportationIdentifier("78A")
                .build();

        BoardingCard card2 = BoardingCard.newBuilder()
                .locationFrom("Barcelona")
                .locationTo("Gerona Airport")
                .transportationType(TransportationType.AIRPORT_BUS)
                .transportationIdentifier("airport bus")
                .build();

        BoardingCard card3 = BoardingCard.newBuilder()
                .locationFrom("Gerona Airport")
                .locationTo("Stockholm")
                .transportationType(TransportationType.FLIGHT)
                .transportationIdentifier("SK455")
                .gate("45B")
                .seat("3A")
                .baggageDropTicketCounter("344")
                .build();

        BoardingCard card4 = BoardingCard.newBuilder()
                .locationFrom("Stockholm")
                .locationTo("New York JFK")
                .transportationType(TransportationType.FLIGHT)
                .transportationIdentifier("SK22")
                .gate("22")
                .seat("7B")
                .build();

        List<BoardingCard> cards = asList(card2, card3, card1, card4);

        List<BoardingCard> sortedCards = new BoardingCardSorter().sortBoardingCards(cards);

        List<String> instructions =
                new BoardingCardInstructionGenerator().generateInstructions(sortedCards);

        for (String instruction : instructions) {
            System.out.println(instruction);
        }
    }
}
