import java.util.ArrayList;
import java.util.List;

class BoardingCardInstructionGenerator {

    private static final String FINAL_MESSAGE = "You have arrived at your final destination.";

    List<String> generateInstructions(final List<BoardingCard> cards) {

        List<String> instructions = new ArrayList<>();
        int instructionNum = 1;
        for (BoardingCard card : cards) {
            String instruction = BoardingCardInstructionGenerator.generateInstruction(card);
            instructions.add(instructionNum + ". " + instruction);
            instructionNum++;
        }

        instructions.add(instructionNum + ". " + FINAL_MESSAGE);

        return instructions;
    }

    private static String generateInstruction(final BoardingCard card) {
        if (card.getTransportationType().equals(TransportationType.TRAIN)) {
            return BoardingCardInstructionGenerator.generateTrainInstruction(card);
        }

        if (card.getTransportationType().equals(TransportationType.AIRPORT_BUS)) {
            return BoardingCardInstructionGenerator.generateAirportBusInstruction(card);
        }

        if (card.getTransportationType().equals(TransportationType.FLIGHT)) {
            return BoardingCardInstructionGenerator.generateFlightInstruction(card);
        }

        throw new IllegalStateException(
                "Transportation type: " + card.getTransportationType() + " is not supported");
    }

    private static String generateTrainInstruction(final BoardingCard card) {
        return "Take train " + card.getTransportationIdentifier()
                + " from " + card.getLocationFrom()
                + " to " + card.getLocationTo()
                + ". Sit in seat " + card.getSeat() + ".";
    }

    private static String generateAirportBusInstruction(final BoardingCard card) {
        return "Take the airport bus"
                + " from " + card.getLocationFrom()
                + " to " + card.getLocationTo()
                + ". No seat assignment.";
    }

    private static String generateFlightInstruction(final BoardingCard card) {
        String baseInstruction = "From " + card.getLocationFrom()
                + ", take flight " + card.getTransportationIdentifier()
                + " to " + card.getLocationTo()
                + ". Gate " + card.getGate()
                + ", seat " + card.getSeat() + ". ";

        String baggageInstruction = card.getBaggageDropTicketCounter() == null
                ? "Baggage will be automatically transferred from your last leg."
                : "Baggage drop at ticket counter " + card.getBaggageDropTicketCounter() + ".";

        return baseInstruction + baggageInstruction;
    }
}
