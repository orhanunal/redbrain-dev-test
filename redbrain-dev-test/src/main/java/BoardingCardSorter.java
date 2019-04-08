import java.util.*;

class BoardingCardSorter {

    List<BoardingCard> sortBoardingCards(final List<BoardingCard> cards) {

        List<BoardingCard> sortedCards = new ArrayList<>();
        Map<String, BoardingCard> cardsToLocationFrom = new HashMap<>();
        Map<String, BoardingCard> cardsToLocationTo = new HashMap<>();

        for (BoardingCard card : cards) {

            if (cardsToLocationFrom.containsKey(card.getLocationFrom())) {
                throw new IllegalStateException(
                        "Duplicate location from: " + card.getLocationFrom());
            }

            if (cardsToLocationTo.containsKey(card.getLocationTo())) {
                throw new IllegalStateException(
                        "Duplicate location to: " + card.getLocationTo());
            }

            cardsToLocationFrom.put(card.getLocationFrom(), card);
            cardsToLocationTo.put(card.getLocationTo(), card);
        }

        Set<String> firstLocationCandidates = new HashSet<>(cardsToLocationTo.keySet());
        firstLocationCandidates.addAll(cardsToLocationFrom.keySet());
        firstLocationCandidates.removeAll(cardsToLocationTo.keySet());

        if (firstLocationCandidates.size() > 1) {
            throw new IllegalStateException(
                    "Found multiple candidates for first location: " + firstLocationCandidates);
        }

        BoardingCard firstCard = cardsToLocationFrom.get(firstLocationCandidates.iterator().next());
        sortedCards.add(firstCard);
        cardsToLocationFrom.remove(firstCard.getLocationFrom());

        BoardingCard nextCard = firstCard;
        while (cardsToLocationFrom.keySet().iterator().hasNext()) {
            nextCard = cardsToLocationFrom.get(nextCard.getLocationTo());
            sortedCards.add(nextCard);
            cardsToLocationFrom.remove(nextCard.getLocationFrom());
        }

        return sortedCards;
    }
}
