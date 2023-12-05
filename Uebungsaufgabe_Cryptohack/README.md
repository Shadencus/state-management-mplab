# Übungsaufgabe: Cryptohack

Deine Aufgabe ist es, die unterschiedlichen Kryptowährungen, welche wir dir über eine fertig
geschriebene Methode zur Verfügung gestellt haben, mithilfe des ViewModels anzuzeigen.

In der Methode greifen wir auf eine API von Coincap 2.0 zu, welche die einzelnen Kryptowährungen
und dessen Daten ausliest und in Form einer Liste zurückgibt.

```kotlin 
fun loadData() {
    val assetService = Asset
    val scope = CoroutineScope(Job() + Dispatchers.IO)
    try {
        scope.launch {
            val res = assetService.assetService.getAllAssets().data
            println(res)
        }
    } catch (err: Error) {
        println(err)
    }
}
```

Aktuell werden Beispieldaten von der folgenden Liste abgerufen:

```kotlin 
val cryptos = listOf(
  CryptoCurrency("1", 1, "BTC", "Bitcoin", 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, ""),
  CryptoCurrency("2", 2, "ETH", "ETH", 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, "")
)
```

## Deine Aufgaben:

1. Mithilfe von ViewModel sollen die Daten geladen werden in der UI
2. Sortiere die Kryptos mithilfe des ViewModels nach
    - Preis
    - Rank
    - Marketcap

### Mockup
![img.png](img.png)
