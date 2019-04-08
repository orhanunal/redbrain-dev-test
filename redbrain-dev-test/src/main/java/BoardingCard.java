import java.util.Objects;

public class BoardingCard {

    private final String locationFrom;
    private final String locationTo;
    private final TransportationType transportationType;
    private final String gate;
    private final String seat;
    private final String transportationIdentifier;
    private final String baggageDropTicketCounter;

    private BoardingCard(Builder builder) {
        locationFrom = builder.locationFrom;
        locationTo = builder.locationTo;
        transportationType = builder.transportationType;
        gate = builder.gate;
        seat = builder.seat;
        transportationIdentifier = builder.transportationIdentifier;
        baggageDropTicketCounter = builder.baggageDropTicketCounter;
    }

    static Builder newBuilder() {
        return new Builder();
    }

    String getLocationFrom() {
        return locationFrom;
    }

    String getLocationTo() {
        return locationTo;
    }

    TransportationType getTransportationType() {
        return transportationType;
    }

    String getGate() {
        return gate;
    }

    String getSeat() {
        return seat;
    }

    String getTransportationIdentifier() {
        return transportationIdentifier;
    }

    String getBaggageDropTicketCounter() {
        return baggageDropTicketCounter;
    }

    public static final class Builder {
        private String locationFrom;
        private String locationTo;
        private TransportationType transportationType;
        private String gate;
        private String seat;
        private String transportationIdentifier;
        private String baggageDropTicketCounter;

        Builder locationFrom(final String locationFrom) {
            this.locationFrom = locationFrom;
            return this;
        }

        Builder locationTo(final String locationTo) {
            this.locationTo = locationTo;
            return this;
        }

        Builder transportationType(final TransportationType transportationType) {
            this.transportationType = transportationType;
            return this;
        }

        Builder gate(final String gate) {
            this.gate = gate;
            return this;
        }

        Builder seat(final String seat) {
            this.seat = seat;
            return this;
        }

        Builder transportationIdentifier(final String transportationIdentifier) {
            this.transportationIdentifier = transportationIdentifier;
            return this;
        }

        Builder baggageDropTicketCounter(final String baggageDropTicketCounter) {
            this.baggageDropTicketCounter = baggageDropTicketCounter;
            return this;
        }

        BoardingCard build() {
            return new BoardingCard(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardingCard that = (BoardingCard) o;
        return Objects.equals(locationFrom, that.locationFrom) &&
                Objects.equals(locationTo, that.locationTo) &&
                transportationType == that.transportationType &&
                Objects.equals(gate, that.gate) &&
                Objects.equals(seat, that.seat) &&
                Objects.equals(transportationIdentifier, that.transportationIdentifier) &&
                Objects.equals(baggageDropTicketCounter, that.baggageDropTicketCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationFrom, locationTo, transportationType, gate, seat, transportationIdentifier, baggageDropTicketCounter);
    }

    @Override
    public String toString() {
        return "BoardingCard{" +
                "locationFrom='" + locationFrom + '\'' +
                ", locationTo='" + locationTo + '\'' +
                ", transportationType=" + transportationType +
                ", gate='" + gate + '\'' +
                ", seat='" + seat + '\'' +
                ", transportationIdentifier='" + transportationIdentifier + '\'' +
                ", baggageDropTicketCounter='" + baggageDropTicketCounter + '\'' +
                '}';
    }
}
