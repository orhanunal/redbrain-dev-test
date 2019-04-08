import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class BoardingCardInstructionGeneratorTest {

    private final BoardingCardInstructionGenerator instructionGenerator
            = new BoardingCardInstructionGenerator();

    @Test
    void generateInstructions_TrainType_ReturnsInstruction() {
        BoardingCard trainCard = BoardingCard.newBuilder()
                .locationFrom("Madrid")
                .locationTo("Barcelona")
                .transportationType(TransportationType.TRAIN)
                .seat("45B")
                .transportationIdentifier("78A")
                .build();

        List<String> instructions =
                instructionGenerator.generateInstructions(singletonList(trainCard));

        assertThat(instructions)
                .containsExactly(
                        "1. Take train 78A from Madrid to Barcelona. Sit in seat 45B.",
                        "2. You have arrived at your final destination.");
    }

    @Test
    void generateInstructions_AirportBusType_ReturnsInstruction() {
        BoardingCard airportBusCard = BoardingCard.newBuilder()
                .locationFrom("Barcelona")
                .locationTo("Gerona Airport")
                .transportationType(TransportationType.AIRPORT_BUS)
                .transportationIdentifier("airport bus")
                .build();

        List<String> instructions =
                instructionGenerator.generateInstructions(singletonList(airportBusCard));

        assertThat(instructions)
                .containsExactly(
                        "1. Take the airport bus from Barcelona to Gerona Airport."
                                + " No seat assignment.",
                        "2. You have arrived at your final destination.");
    }

    @Test
    void generateInstructions_FlightTypeWithBaggage_ReturnsInstruction() {
        BoardingCard flightCard = BoardingCard.newBuilder()
                .locationFrom("Gerona Airport")
                .locationTo("Stockholm")
                .transportationType(TransportationType.FLIGHT)
                .transportationIdentifier("SK455")
                .gate("45B")
                .seat("3A")
                .baggageDropTicketCounter("344")
                .build();

        List<String> instructions =
                instructionGenerator.generateInstructions(singletonList(flightCard));

        assertThat(instructions)
                .containsExactly(
                        "1. From Gerona Airport, take flight SK455 to Stockholm."
                                + " Gate 45B, seat 3A."
                                + " Baggage drop at ticket counter 344.",
                        "2. You have arrived at your final destination.");
    }

    @Test
    void generateInstructions_FlightTypeNoBaggage_ReturnsInstruction() {
        BoardingCard flightCard = BoardingCard.newBuilder()
                .locationFrom("Gerona Airport")
                .locationTo("Stockholm")
                .transportationType(TransportationType.FLIGHT)
                .transportationIdentifier("SK455")
                .gate("45B")
                .seat("3A")
                .build();

        List<String> instructions =
                instructionGenerator.generateInstructions(singletonList(flightCard));

        assertThat(instructions)
                .containsExactly(
                        "1. From Gerona Airport, take flight SK455 to Stockholm."
                                + " Gate 45B, seat 3A."
                                + " Baggage will be automatically transferred from your last leg.",
                        "2. You have arrived at your final destination.");
    }

    @Test
    void generateInstructions_MultipleTransportationTypes_ReturnsInstructions() {
        BoardingCard trainCard = BoardingCard.newBuilder()
                .locationFrom("Madrid")
                .locationTo("Barcelona")
                .transportationType(TransportationType.TRAIN)
                .seat("45B")
                .transportationIdentifier("78A")
                .build();

        BoardingCard airportBusCard = BoardingCard.newBuilder()
                .locationFrom("Barcelona")
                .locationTo("Gerona Airport")
                .transportationType(TransportationType.AIRPORT_BUS)
                .transportationIdentifier("airport bus")
                .build();

        BoardingCard flightCard1 = BoardingCard.newBuilder()
                .locationFrom("Gerona Airport")
                .locationTo("Stockholm")
                .transportationType(TransportationType.FLIGHT)
                .transportationIdentifier("SK455")
                .gate("45B")
                .seat("3A")
                .baggageDropTicketCounter("344")
                .build();

        BoardingCard flightCard2 = BoardingCard.newBuilder()
                .locationFrom("Gerona Airport")
                .locationTo("Stockholm")
                .transportationType(TransportationType.FLIGHT)
                .transportationIdentifier("SK455")
                .gate("45B")
                .seat("3A")
                .build();

        List<String> instructions =
                instructionGenerator.generateInstructions(
                        asList(trainCard, airportBusCard, flightCard1, flightCard2));

        assertThat(instructions)
                .containsExactly(
                        "1. Take train 78A from Madrid to Barcelona. Sit in seat 45B.",
                        "2. Take the airport bus from Barcelona to Gerona Airport."
                                + " No seat assignment.",
                        "3. From Gerona Airport, take flight SK455 to Stockholm."
                                + " Gate 45B, seat 3A."
                                + " Baggage drop at ticket counter 344.",
                        "4. From Gerona Airport, take flight SK455 to Stockholm."
                                + " Gate 45B, seat 3A."
                                + " Baggage will be automatically transferred from your last leg.",
                        "5. You have arrived at your final destination.");
    }
}