import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderImplTest {

    @ParameterizedTest
    @MethodSource("sendSource")
    public void sendTest(String ip, String expected) {
        //arrange
        Map<String, String> map = new HashMap<>();
        map.put("x-real-ip", ip);

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.0.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(geoService.byIp("172"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp("96"))
                .thenReturn(new Location("New York", Country.USA, null, 0));


        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        //act
        String result = messageSender.send(map);

        //assert
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> sendSource() {
        return Stream.of(
                Arguments.of("172.0.32.11", "Добро пожаловать"),
                Arguments.of("96.44.183.149", "Welcome"),
                Arguments.of("172", "Добро пожаловать"),
                Arguments.of("96", "Welcome"),
                Arguments.of("", "Welcome")
        );
    }
}
