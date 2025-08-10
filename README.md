# e-biznes

Zadania z przedmiotu e-biznes

## **Zadanie 1** Docker

[dockerhub](https://hub.docker.com/r/tomaszpakula/ebiznes01-docker)

✅ 3.0 obraz ubuntu z Pythonem w wersji 3.10 [commit 1](https://github.com/tomaszpakula/e-biznes/commit/8e7d3b017218db3459e5aa33c2ad5d9719d78071)

✅ 3.5 obraz ubuntu:24.02 z Javą w wersji 8 oraz Kotlinem [commit 1](https://github.com/tomaszpakula/e-biznes/commit/8e7d3b017218db3459e5aa33c2ad5d9719d78071)

✅ 4.0 do powyższego należy dodać najnowszego Gradle’a oraz paczkę JDBC
SQLite w ramach projektu na Gradle (build.gradle) [commit 1](https://github.com/tomaszpakula/e-biznes/commit/8e7d3b017218db3459e5aa33c2ad5d9719d78071)

✅ 4.5 stworzyć przykład typu HelloWorld oraz uruchomienie aplikacji
przez CMD oraz gradle [commit 1](https://github.com/tomaszpakula/e-biznes/commit/8e7d3b017218db3459e5aa33c2ad5d9719d78071)

✅ 5.0 dodać konfigurację docker-compose [commit 1](https://github.com/tomaszpakula/e-biznes/commit/8e7d3b017218db3459e5aa33c2ad5d9719d78071)

[Katalog](https://github.com/tomaszpakula/e-biznes/tree/main/zadanie1)

## **Zadanie 2** Scala

✅ 3.0  Należy stworzyć kontroler do Produktów [commit 1](https://github.com/tomaszpakula/e-biznes/commit/f68969f3d6a1dee978a7fb37545ff8f81d531df2)

✅ 3.5 Do kontrolera należy stworzyć endpointy zgodnie z CRUD - dane
pobierane z listy [commit 1](https://github.com/tomaszpakula/e-biznes/commit/f68969f3d6a1dee978a7fb37545ff8f81d531df2)

✅ 4.0 Należy stworzyć kontrolery do Kategorii oraz Koszyka + endpointy
zgodnie z CRUD [commit 1](https://github.com/tomaszpakula/e-biznes/commit/f68969f3d6a1dee978a7fb37545ff8f81d531df2)

✅ 4.5 Należy aplikację uruchomić na dockerze (stworzyć obraz) oraz dodać
skrypt uruchamiający aplikację via ngrok [commit 2](https://github.com/tomaszpakula/e-biznes/commit/c1b69220879ec359547e7d8192c4baf28bdaf86a)

✅ 5.0 Należy dodać konfigurację CORS dla dwóch hostów dla metod CRUD [commit 2](https://github.com/tomaszpakula/e-biznes/commit/c1b69220879ec359547e7d8192c4baf28bdaf86a)

[Katalog](https://github.com/tomaszpakula/e-biznes/tree/main/zadanie2)

## **Zadanie 3** Kotlin

✅ 3.0 Należy stworzyć aplikację kliencką w Kotlinie we frameworku Ktor, która pozwala na przesyłanie wiadomości na platformę Discord [commit 1](https://github.com/tomaszpakula/e-biznes/commit/1d663e2fdde759d5687a63b63b5cd884aeba9c7b)

✅  3.5 Aplikacja jest w stanie odbierać wiadomości użytkowników z platformy Discord skierowane do aplikacji (bota) [commit 1](https://github.com/tomaszpakula/e-biznes/commit/1d663e2fdde759d5687a63b63b5cd884aeba9c7b)

✅  4.0 Zwróci listę kategorii na określone żądanie użytkownika [commit 2](https://github.com/tomaszpakula/e-biznes/commit/c4c6bfc3430f09684658f379b4e17c8cb6146de9)

✅ 4.5 Zwróci listę produktów wg żądanej kategorii [commit 3](https://github.com/tomaszpakula/e-biznes/commit/2c16733b7e1eb8809376bea7dbaf1ee43bb90986)

✅ 5.0 Aplikacja obsłuży dodatkowo jedną z platform: Slack, Messenger, Webex [commit 4](https://github.com/tomaszpakula/e-biznes/commit/6cdbfd0261c530de5cac3217604a4bda83e1bdb5)

[katalog](https://github.com/tomaszpakula/e-biznes/tree/main/zadanie3)

## **Zadanie 4** Go

✅ 3.0 Należy stworzyć aplikację we frameworki echo w j. Go, która będzie miała kontroler Produktów zgodny z CRUD 

✅ 3.5 Należy stworzyć model Produktów wykorzystując gorm oraz wykorzystać model do obsługi produktów (CRUD) w kontrolerze (zamiast listy) [commit](https://github.com/tomaszpakula/e-biznes/commit/2d66f84fa1c4cc304bdb0c925410caf8664740b8)

✅ 4.0 Należy dodać model Koszyka oraz dodać odpowiedni endpoint [commit](https://github.com/tomaszpakula/e-biznes/commit/34d4402b1e2cdfff24eaa863aa9c27deb9011746)

✅ 4.5 Należy stworzyć model kategorii i dodać relację między kategorią, a produktem [commit](https://github.com/tomaszpakula/e-biznes/commit/2cb36be657b889fb1484a0551f3e85b5cbdb82ad)

✅  5.0 pogrupować zapytania w gorm’owe scope'y [commit](https://github.com/tomaszpakula/e-biznes/commit/2037e2871b8f388de51f71c23add41239e428e38)

[katalog](https://github.com/tomaszpakula/e-biznes/tree/main/zadanie4)


## **Zadanie 5** Frontend

Należy stworzyć aplikację kliencką wykorzystując bibliotekę React.js.
W ramach projektu należy stworzyć trzy komponenty: Produkty, Koszyk
oraz Płatności. Koszyk oraz Płatności powinny wysyłać do aplikacji
serwerowej dane, a w Produktach powinniśmy pobierać dane o produktach
z aplikacji serwerowej. Aplikacja serwera w jednym z trzech języków:
Kotlin, Scala, Go. Dane pomiędzy wszystkimi komponentami powinny być
przesyłane za pomocą React hooks.

✅ 3.0 W ramach projektu należy stworzyć dwa komponenty: Produkty oraz
Płatności; Płatności powinny wysyłać do aplikacji serwerowej dane, a w
Produktach powinniśmy pobierać dane o produktach z aplikacji
serwerowej; [commit](https://github.com/tomaszpakula/e-biznes/commit/d74bbeaacebce0ec0e4e3c24677ec07d45f13d82)

🟩 3.5 Należy dodać Koszyk wraz z widokiem; należy wykorzystać routing [commit](https://github.com/tomaszpakula/e-biznes/commit/a0a2f4742d7de75eee6125ec6aac51701ae11c47)

✅ 4.0 Dane pomiędzy wszystkimi komponentami powinny być przesyłane za
pomocą React hooks [commit](https://github.com/tomaszpakula/e-biznes/commit/a0a2f4742d7de75eee6125ec6aac51701ae11c47)

✅ 4.5 Należy dodać skrypt uruchamiający aplikację serwerową oraz
kliencką na dockerze via docker-compose [commit](https://github.com/tomaszpakula/e-biznes/commit/92723ac26fe01d23d0b628530ea7355dc24e7c5c)

✅ 5.0 Należy wykorzystać axios’a oraz dodać nagłówki pod CORS [commit](https://github.com/tomaszpakula/e-biznes/commit/92723ac26fe01d23d0b628530ea7355dc24e7c5c)

[katalog](https://github.com/tomaszpakula/e-biznes/tree/main/zadanie5)

## **Zadanie 6** Testy

Należy stworzyć 20 przypadków testowych w jednym z rozwiązań:

- Cypress JS (JS)
- Selenium (Kotlin, Python, Java, JS, Go, Scala)

Testy mają w sumie zawierać minimum 50 asercji (3.5). Mają również
uruchamiać się na platformie Browserstack (5.0). Proszę pamiętać o
stworzeniu darmowego konta via https://education.github.com/pack.

✅ 3.0 Należy stworzyć 20 przypadków testowych w CypressJS lub Selenium
(Kotlin, Python, Java, JS, Go, Scala) [commit](https://github.com/tomaszpakula/e-biznes/commit/e8d564106b1e62a84e3328b0368829f62a08b321)

✅ 3.5 Należy rozszerzyć testy funkcjonalne, aby zawierały minimum 50 asercji [commit](https://github.com/tomaszpakula/e-biznes/commit/e8d564106b1e62a84e3328b0368829f62a08b321)

✅ 4.0 Należy stworzyć testy jednostkowe do wybranego wcześniejszego
projektu z minimum 50 asercjami

✅ 4.5 Należy dodać testy API, należy pokryć wszystkie endpointy z
minimum jednym scenariuszem negatywnym per endpoint [commit](https://github.com/tomaszpakula/e-biznes/commit/1ed5726daba17ab0dc79e96ed615e407b8b8c246)

🔲 5.0 Należy uruchomić testy funkcjonalne na Browserstacku

[katalog](https://github.com/tomaszpakula/e-biznes/tree/main/zadanie6)

## **Zadanie 7** Sonar

Należy dodać projekt aplikacji klienckiej oraz serwerowej (jeden
branch, dwa repozytoria) do Sonara w wersji chmurowej
(https://sonarcloud.io/). Należy poprawić aplikacje uzyskując 0 bugów,
0 zapaszków, 0 podatności, 0 błędów bezpieczeństwa. Dodatkowo należy
dodać widżety sonarowe do README w repozytorium dane projektu z
wynikami.

✅ 3.0 Należy dodać litera do odpowiedniego kodu aplikacji serwerowej w
hookach gita

✅ 3.5 Należy wyeliminować wszystkie bugi w kodzie w Sonarze (kod
aplikacji serwerowej)

✅ 4.0 Należy wyeliminować wszystkie zapaszki w kodzie w Sonarze (kod
aplikacji serwerowej)

✅ 4.5 Należy wyeliminować wszystkie podatności oraz błędy bezpieczeństwa
w kodzie w Sonarze (kod aplikacji serwerowej) [commit](https://github.com/tomaszpakula/e-biznes-server/commit/3e54668c3e1217bcce4b9cd2756ecd4a64b29007)

✅ 5.0 Należy wyeliminować wszystkie błędy oraz zapaszki w kodzie
aplikacji klienckiej [commit](https://github.com/tomaszpakula/e-biznes-client/commit/a52f9fdabad4e0d3b4a5de0794c38021189ba7c1)

[katalog](https://github.com/tomaszpakula/e-biznes/tree/main/zadanie7)

[sonar client](https://sonarcloud.io/project/overview?id=tomaszpakula_e-biznes-client)

[sonar_server](https://sonarcloud.io/project/overview?id=tomaszpakula_e-biznes-server)



## **Zadanie 8** Oauth2

Należy skonfigurować klienta Oauth2 (4.0). Dane o użytkowniku wraz z
tokenem powinny być przechowywane po stronie bazy serwera, a nowy
token (inny niż ten od dostawcy) powinien zostać wysłany do klienta
(React). Można zastosować mechanizm sesji lub inny dowolny (5.0).
Zabronione jest tworzenie klientów bezpośrednio po stronie React'a
wyłączając z komunikacji aplikację serwerową, np. wykorzystując auth0.

Prawidłowa komunikacja: react-sewer-dostawca-serwer(via return
uri)-react.

✅ 3.0 logowanie przez aplikację serwerową (bez Oauth2)

✅ 3.5 rejestracja przez aplikację serwerową (bez Oauth2)

✅ 4.0 logowanie via Google OAuth2

✅ 4.5 logowanie via Facebook lub Github OAuth2

✅ 5.0 zapisywanie danych logowania OAuth2 po stronie serwera

[katalog](https://github.com/tomaszpakula/e-biznes/tree/main/zadanie8)

## **Zadanie 9** ChatGPT bot

Należy rozszerzyć funkcjonalność wcześniej stworzonego bota. Do niego
należy stworzyć aplikajcę frontendową, która połączy się z osobnym
serwisem, który przeanalizuje tekst od użytkownika i prześle zapytanie
do GPT, a następnie prześle odpowiedź do użytkownika. Cały projekt
należy stworzyć w Pythonie.

✅ 3.0 należy stworzyć po stronie serwerowej osobny serwis do łącznia z
chatGPT do usługi chat

✅ 3.5 należy stworzyć interfejs frontowy dla użytkownika, który
komunikuje się z serwisem; odpowiedzi powinny być wysyłane do
frontendowego interfejsu

✅ 4.0 stworzyć listę 5 różnych otwarć oraz zamknięć rozmowy

✅ 4.5 filtrowanie po zagadnieniach związanych ze sklepem (np.
ograniczenie się jedynie do ubrań oraz samego sklepu) do GPT

✅ 5.0 filtrowanie odpowiedzi po sentymencie

## **Zadanie 10** Chmura/CI

Należy wykorzystać GitHub Actions (dopuszczalne są inne rozwiązania
CI) oraz chmurę Azure (dopuszczalne inne chmury), aby zbudować oraz
zdeployować aplikację kliencką (frontend) oraz serwerową (backend)
jako osobne dwie aplikacje. Należy do tego wykorzystać obrazy
dockerowe, a aplikacje powinny działać na kontenerach. Dopuszczalne
jest zbudowanie wcześniej aplikacji (jar package) oraz budowanie
aplikacji via Github Actions. Należy zwrócić uwagę na zasoby dostępne
na chmurze.

✅ 3.0 Należy stworzyć odpowiednie instancje po stronie chmury na
dockerze

✅ 3.5 Stworzyć odpowiedni pipeline w Github Actions do budowania
aplikacji (np. via fatjar)

✅ 4.0 Dodać notyfikację mailową o zbudowaniu aplikacji

✅ 4.5 Dodać krok z deploymentem aplikacji serwerowej oraz klienckiej na
chmurę

🔲 5.0 Dodać uruchomienie regresyjnych testów automatycznych
(funkcjonalnych) jako krok w Actions

[serwer](https://github.com/tomaszpakula/ebiznes-server2)
[klient](https://github.com/tomaszpakula/ebiznes-client2)
