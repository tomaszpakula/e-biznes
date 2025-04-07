# e-biznes

Zadania z przedmiotu e-biznes

**Zadanie 1** Docker

:white_check_mark: 3.0 obraz ubuntu z Pythonem w wersji 3.10 [Link do commita 1](https://github.com/tomaszpakula/e-biznes/commit/8e7d3b017218db3459e5aa33c2ad5d9719d78071)

:white_check_mark: 3.5 obraz ubuntu:24.02 z Javą w wersji 8 oraz Kotlinem [Link do commita 1](https://github.com/tomaszpakula/e-biznes/commit/8e7d3b017218db3459e5aa33c2ad5d9719d78071)

:white_check_mark: 4.0 do powyższego należy dodać najnowszego Gradle’a oraz paczkę JDBC
SQLite w ramach projektu na Gradle (build.gradle) [Link do commita 1](https://github.com/tomaszpakula/e-biznes/commit/8e7d3b017218db3459e5aa33c2ad5d9719d78071)

:white_check_mark: 4.5 stworzyć przykład typu HelloWorld oraz uruchomienie aplikacji
przez CMD oraz gradle [Link do commita 1](https://github.com/tomaszpakula/e-biznes/commit/8e7d3b017218db3459e5aa33c2ad5d9719d78071)

:white_check_mark: 5.0 dodać konfigurację docker-compose [Link do commita 1](https://github.com/tomaszpakula/e-biznes/commit/8e7d3b017218db3459e5aa33c2ad5d9719d78071)

[Kod](https://github.com/tomaszpakula/e-biznes/tree/main/zadanie1)

**Zadanie 2** Scala

:white_check_mark: 3.0  Należy stworzyć kontroler do Produktów [Link do commita 1](https://github.com/tomaszpakula/e-biznes/commit/f68969f3d6a1dee978a7fb37545ff8f81d531df2)

:white_check_mark: 3.5 Do kontrolera należy stworzyć endpointy zgodnie z CRUD - dane
pobierane z listy [Link do commita 1](https://github.com/tomaszpakula/e-biznes/commit/f68969f3d6a1dee978a7fb37545ff8f81d531df2)

:white_check_mark: 4.0 Należy stworzyć kontrolery do Kategorii oraz Koszyka + endpointy
zgodnie z CRUD [Link do commita 1](https://github.com/tomaszpakula/e-biznes/commit/f68969f3d6a1dee978a7fb37545ff8f81d531df2)

:white_check_mark: 4.5 Należy aplikację uruchomić na dockerze (stworzyć obraz) oraz dodać
skrypt uruchamiający aplikację via ngrok [Link do commita 2](https://github.com/tomaszpakula/e-biznes/commit/c1b69220879ec359547e7d8192c4baf28bdaf86a)

:white_check_mark: 5.0 Należy dodać konfigurację CORS dla dwóch hostów dla metod CRUD [Link do commita 2](https://github.com/tomaszpakula/e-biznes/commit/c1b69220879ec359547e7d8192c4baf28bdaf86a)
[Kod](https://github.com/tomaszpakula/e-biznes/tree/main/zadanie2)

**Zadanie 3** Kotlin

✅ 3.0 Należy stworzyć aplikację kliencką w Kotlinie we frameworku Ktor, która pozwala na przesyłanie wiadomości na platformę Discord

✅  3.5 Aplikacja jest w stanie odbierać wiadomości użytkowników z platformy Discord skierowane do aplikacji (bota)

✅  4.0 Zwróci listę kategorii na określone żądanie użytkownika

✅ 4.5 Zwróci listę produktów wg żądanej kategorii

✅ 5.0 Aplikacja obsłuży dodatkowo jedną z platform: Slack, Messenger, Webex

**Zadanie 4** Go

:black_large_square: 3.0 Należy stworzyć aplikację we frameworki echo w j. Go, która będzie miała kontroler Produktów zgodny z CRUD

:black_large_square: 3.5 Należy stworzyć model Produktów wykorzystując gorm oraz wykorzystać model do obsługi produktów (CRUD) w kontrolerze (zamiast listy)

:black_large_square: 4.0 Należy dodać model Koszyka oraz dodać odpowiedni endpoint

:black_large_square: 4.5 Należy stworzyć model kategorii i dodać relację między kategorią, a produktem

:black_large_square: 5.0 pogrupować zapytania w gorm’owe scope'y


**Zadanie 5** Frontend

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
serwerowej;

🔳 3.5 Należy dodać Koszyk wraz z widokiem; należy wykorzystać routing

🔳 4.0 Dane pomiędzy wszystkimi komponentami powinny być przesyłane za
pomocą React hooks

🔳 4.5 Należy dodać skrypt uruchamiający aplikację serwerową oraz
kliencką na dockerze via docker-compose

🔳 5.0 Należy wykorzystać axios’a oraz dodać nagłówki pod CORS