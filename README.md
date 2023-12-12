# redis-cache

Uses Redis Cache to store user sessions.

1. **Redis Baglanti Ayarlari:**
    - `host`: Redis sunucusunun konumunu belirtir. Bu ornekte, Redis localhost'ta calisiyor gibi gorunmektedir.
    - `port`: Redis sunucunun dinledigi port numarasini belirtir. Bu ornekte, 6379 portu kullaniliyor.
    - `timeout`: Redis baglantisi icin zaman asimini belirtir. Bu ornekte, 10 saniye (10000 milisaniye) olarak ayarlanmis.

2. **Lettuce (Redis icin Java temelli bir istemci) Havuzu Ayarlari:**
    - `lettuce`: Bu bolum, Redis ile etkilesimde bulunmak icin kullanilan Lettuce kutuphanesinin ayarlarini icerir.
    - `pool`: Redis baglanti havuzu yapilandirmasi.
        - `max-active`: Eszamanli olarak etkin baglanti sayisini belirtir.
        - `max-wait`: Bir baglanti almak icin maksimum bekleme suresini belirtir. -1, belirli bir sure beklenmeksizin beklemek anlamina gelir.
        - `max-idle`: Havuzda izin verilen maksimum bos baglanti sayisini belirtir.
        - `min-idle`: Havuzda izin verilen minimum bos baglanti sayisini belirtir.

3. **Onbellek Ayarlari:**
    - `cache`: Spring Cache moduluyle ilgili genel onbellek ayarlarini icerir.
    - `type`: Kullanilacak onbellek turunu belirtir. Bu ornekte, Redis onbellegi kullaniliyor.
    - `redis`: Redis onbellek ozel ayarlarini icerir.
        - `time-to-live`: Onbellekteki bir ogenin ne kadar sureyle saklanacagini belirtir (saniye cinsinden).
        - `cache-null-values`: `true` olarak ayarlandiginda, onbellege `null` degerler de dahil edilir.

## Tech Stack

- Java 17
- Spring Boot 3.0
- Spring Data JPA
- Redis
- PostgreSQL
- Docker
- Lombok

## Requirements

For building and running the application you need:

- [JDK 17 or newer](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org)
- [Redis](https://redis.io/)
- [PostgreSQL](https://www.postgresql.org/)
- [Lombok](https://projectlombok.org/)
- [Docker](https://www.docker.com/)

## Build & Run

```shell
  docker-compose -f docker-compose.yml up -d
```

```shell
  mvn clean install && mvn --projects backend spring-boot:run
```