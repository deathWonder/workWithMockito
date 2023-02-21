import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;


public class GeoServiceImplTest {

    GeoServiceImpl geoService = new GeoServiceImpl();

    @ParameterizedTest
    @MethodSource("byIpSource")
    public void byIpTest(String ip, Location expected) {
        //act
        Location result = geoService.byIp(ip);

        //assert
        Assertions.assertEquals(expected, result);
    }

    //arrange
    public static Stream<Arguments> byIpSource() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null, 0)),
                Arguments.of("95", null)

        );
    }

    @ParameterizedTest
    @MethodSource("byCoordinatesSource")
    public void byCoordinatesTest(double latitude, double longitude) {
        Assertions.assertThrowsExactly(RuntimeException.class, () -> geoService.byCoordinates(latitude, longitude));
    }

    public static Stream<Arguments> byCoordinatesSource() {
        return Stream.of(
                Arguments.of(10.5, 14.5),
                Arguments.of(1213.5, 14.235),
                Arguments.of(120.5, 1454.5)
        );
    }
}
