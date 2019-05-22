# Osztott rendszerek kidolgozás

# Bevezetés

## Definíció: Elosztott rendszer

Az elosztott rendszer önálló számítógépek olyan összessége, amely kezeloi számára egyetlen koherens rendszernek tűnik.

## Az elosztott rendszer célja

-   Távoli eroforrások elérhetővé tétele
-   Átlátszóság (distribution transparency)
-   Nyitottság (openness)
-   Skálázhatóság (scalability)

## Az átlátszóság mértéke

-   A teljes átlátszóságra törekvés általában túl eros:
    -   A felhasználók különbözo kontinenseken tartózkodhatnak
    -   A hálózatok és az egyes gépek meghibásodásának teljes elfedése elméletileg és gyakorlatilag is lehetetlen
        -   Nem lehet eldönteni, hogy a szerver csak lassan válaszol vagy meghibásodott
        -   Távolról nem megállapítható, hogy a szerver feldolgozta-e a kérésünket, mielott összeomlott
    -   A nagymértékű átlátszóság a hatékonyság rovására megy, de a késleltetést is el szeretnénk rejteni
        -   Ilyen feladat lehet a webes gyorsítótárak (cache-ek) tökéletesen frissen tartása
        -   Másik példa: minden változás azonnal lemezre írása nagymértékű hibatűréshez

## Elosztott rendszerek nyitottsága

### Nyitott elosztott rendszer

A rendszer képes más nyitott rendszerek számára szolgáltatásokat nyújtani, és azok szolgáltatásait igénybe venni:

-   A rendszerek jól definiált interfészekkel rendelkeznek
-   Az alkalmazások hordozhatóságát (portability) minél inkább támogatják
-   Könnyen elérheto a rendszerek együttműködése (interoperability)

A nyitottság elérése

A nyitott elosztott rendszer legyen könnyen alkalmazható heterogén környezetben, azaz különböző

-   hardvereken,
-   platformokon,
-   programozási nyelveken.

### A nyitottság implementálása

-   Fontos, hogy a rendszer könnyen cserélheto részekből álljon
-   Belso interfészek használata, nem egyetlen monolitikus rendszer
-   A rendszernek minél jobban paraméterezhetonek kell lennie
-   Egyetlen komponens megváltoztatása/cseréje lehetoleg minél kevésbé hasson a rendszer más részeire

### Átméretezhetoség

Ha egy „kis" rendszer megno, az sokfajta kihívást jelenthet. Több különböző jellege is megnohet a rendszernek:

-   méret szerinti átméretezhetoség: több felhasználó és/vagy folyamat van a rendszerben
-   földrajzi átméretezhetoség: a rendszert nagyobb területrol veszik igénybe, pl. egyetemen belüli felhasználás→világméretű felhasználóbázis
-   adminisztrációs átméretezhetoség : biztonsági, karbantartási, együttműködési kérdések merülnek fel, ha új adminisztrációs tartományok kerülnek a rendszerbe

## Technikák az átméretezhetoség megvalósítására

### A kommunikációs késleltetés elfedése

A válaszra várás közben más tevékenység végzése:

-   Aszinkron kommunikáció használata
-   A beérkezo választ külön kezelő dolgozza fel
-   Probléma: nem minden alkalmazás ültetheto át ilyen megközelítésre

### Elosztás

Az adatokat és a számításokat több számítógép tárolja/végzi:

-   A számítások egy részét a kliensoldal végzi (Java appletek)
-   Decentralizált elnevezési rendszerek (DNS)
-   Decentralizált információs rendszerek (WWW)

### Replikáció/cache-elés

Több számítógép tárolja egy adat másolatait:

-   Replikált fájlszerverek és adatbázisok
-   Tükrözött weboldalak
-   Weboldalak cache-elése (böngészokben, proxy szervereken)
-   Fájlok cache-elése (a szerver- és kliensoldalon)

## Átméretezhetoség – a probléma

### Megjegyzés

Az átméretezhetoség könnyen elérhető, de ára van:

-   Több másolat fenntartása (cache vagy replika) inkonzisztenciához vezet: ha módosítunk egy másolatot, az eltér a többitol.
-   A másolatok konzisztensen tartásához globális szinkronizációra van szükség minden egyes változtatás után.
-   A globális szinkronizáció viszont rosszul skálázható nagy rendszerekre.

## Következmény

Ha feladjuk a globális szinkronizációt, akkor kénytelenek vagyunk bizonyos fokú inkonzisztenciát elviselni a rendszerünkben. Az, hogy ez milyen mértékben elfogadható, rendszerfüggő.

## Elosztott rendszerek fejlesztése: hibalehetoségek

### Megjegyzés

Az elosztott rendszer környezetérol kényelmes lehet feltételezni, hogy megbízható. Ha ez tévesnek bizonyul, az a rendszer újratervezéséhez vezethet. Néhány ilyen feltételezés:

-   a hálózat hibamentes
-   a hálózat biztonságos
-   a hálózat homogén
-   a hálózati topológia nem változik
-   a kommunikációnak nincsen idoigénye
-   a sávszélesség korlátlan
-   a kommunikációnak nincsen költsége
-   csak egy adminisztrátor van

## Elosztott rendszerek fajtái

-   Elosztott számítási rendszerek
    -   grid
    -   cluster
    -   cloud
-   Elosztott információs rendszerek
-   Elosztott átható (pervasive, ubiquitous) rendszerek

## Elosztott számítási rendszerek

### Számítási rendszer

Sok elosztott rendszer célja számítások végzése nagy teljesítménnyel.

### Cluster (fürt)

Lokális hálózatra kapcsolt számítógépek összessége.

-   Homogén: ugyanaz az operációs rendszer, hardveresen nem vagy csak alig térnek el
-   A vezérlés központosítva van, általában egyetlen gépre

### Grid (rács)

Több gép, kevésbé egységesek:

-   Heterogén architektúra
-   Átívelhet több szervezeti egységen
-   Nagyméretű hálózatokra terjedhet ki

### Felho (cloud)

Többrétegű architektúra.

#### Négy különbözo réteg:

-   Hardver: Processzorok, útválasztók (routerek), áramforrások, hűtoberendezések. A felhasználók közvetlenül nem látják.
-   Infrastruktúra: Virtuális hardvert tesz elérhetové: szerver, adattároló, hálózati kapcsolat, számítási kapacitás lefoglalása és kezelése.
-   Platform: Magasabb szintű absztrakciókat biztosít. Pl. az Amazon S3 társzolgáltatás különbözo fájlműveleteket biztosít; a felhasználónak vödrei (bucket) vannak, ebbe feltölthet, letölthet stb. fájlokat egy API segítségével.
-   Alkalmazás: A végfelhasználónak szánt, jellemzoen grafikus felületű alkalmazások.

## Elosztott információs rendszerek

### Definíció

Sok elosztott rendszer elsodleges célja adatok kezelése, illetve meglevo ilyen rendszerek elérése. Példa: tranzakciókezelo rendszerek

## Elosztott információs rendszerek: tranzakciók

Modell

A tranzakció adatok összességén (adatbázis, objektumok vagy más adattár) végzett művelet (lehetnek részműveletei), melynek az alábbi tulajdonságai vannak. A kezdobetűk rövidítéséből ACID-nek szokás nevezni a követelményrendszert

-   Atomicity (atomosság): Vagy a tranzakció minden eleme sikerrel végrehajtódik, vagy egyik sem.
-   Consistency (konzisztencia): A tranzakció végével konzisztens állapot kell, hogy fent álljon. (közben elofordulhat inkonzisztencia)
-   Isolation (elkülöníthetoség): Minden tranzakciónak úgy kell végbemennie, mintha az lenne az egyetlen aktív tranzakció, vagyis elkülöníthetoek legyenek egymástól és ne befolyásolják egymást semmilyen módon.
-   Durability (tartósság): A tranzakció által végrehajtott módosításokat tartós adattárolóra kell írni, hogy esetleges rendszerhiba esetén visszaállíthatóak legyenek.

### Tranzakciófeldolgozó monitor

A tranzakciókat sokszor több szerver hajtja végre. Ezeket egy TP monitor vezérli.

## Alkalmazásintegráció nagy rendszerekben

A TP monitor nem választja el az alkalmazásokat az adatbázisoktól. Továbbá az alkalmazásoknak egymással is kommunikálniuk kell.

-   Távoli eljáráshívás (Remote Procedure Call, RPC)
-   Üzenetorientált köztesréteg (Message-Oriented Middleware, MOM)

## Elosztott átható rendszerek

Sok modern elosztott rendszer kicsi, mobil elemekbol áll.

### Néhány jellemző

-   A környezet megváltozhat: A rendszernek ezt követnie kell.
-   Ad hoc szervezodés: A rendszer komponenseit nagyon különböző módokon használhatják a felhasználók. Ezért a rendszernek könnyen konfigurálhatónak kell lennie.
-   Megosztott szolgáltatások: Mivel a rendszer nagyon változékony, az adatoknak könnyen kell áramlaniuk. Ennek elosegítésére a rendszer elemei általában nagyon egyszerű szerkezetűek.

## Érzékelohálózatok

Az érzékeloket tartalmazó csúcsok

-   sok van belolük (nagyságrendileg 10-1000 darab)
-   egyszerűek (kevés memória, számítási és kommunikációs kapacitás)
-   sokszor elemrol működnek, vagy áramforrás sem szükséges hozzájuk

# Architektúrák

-   Architektúrafajták
-   Szoftverarchitektúrák
-   Architektúrák és köztesréteg
-   Az elosztott rendszerek önszervezése

## Architektúrafajták

A rendszer elemeit szervezzük logikai szerepük szerint különböző komponensekbe, és ezeket osszuk el a rendszer gépein.

![](tobbretegu.PNG)

(a) A többrétegű megközelítés kliens-szerver rendszerek esetén jól működik

(b) Itt a komponensek (objektumok) összetettebb struktúrában kommunikálnak, mindegyik közvetlenül küld üzeneteket a többieknek

A komponensek közötti kommunikáció történhet közvetlen kapcsolat nélkül („anonim"), illetve egyidejűség nélkül („aszinkron").

![](aszinkron.PNG)

(a) Publish/subscribe modell (térben független)

(b) Megosztott, perzisztens adattár (térben és idoben független)

## Központosított architektúrák

### Egyszerű kliens–szerver modell

Jellemzoi:
egyes folyamatok szolgáltatásokat ajánlanak ki (ezek a szerverek) más folyamatok ezeket a szolgáltatásokat szeretnék használni (ezek a kliensek) a kliensek és a szerverek különbözo gépeken lehetnek a kliens kérést küld (amire a szerver válaszol), így veszi igénybe a szolgáltatást

## Többrétegű architektúrák

### Elosztott információs rendszerek rétegelése

Az elosztott információs rendszerek gyakran három logikai rétegre („layer" vagy „tier") vannak tagolva.

### Háromrétegű architektúra

-   Megjelenítés (user interface): az alkalmazás felhasználói felületét alkotó komponensekbol áll
-   Üzleti logika (application): az alkalmazás működését írja le (konkrét adatok nélkül)
-   Perzisztencia (data layer): az adatok tartós tárolása

A három rétegbol néha több is egy gépen található meg.
Kétrétegű architektúra: kliens/egyszerű szerver Egyrétegű architektúra: nagygépre (mainframe) kötött terminal

## Decentralizált architektúrák

### Peer-to-peer architektúra

Az utóbbi években a peer-to-peer (P2P) architektúra egyre népszerűbbé válik. A „peer" szó arra utal, hogy a csúcsok között (többnyire) nincsenek kitüntetett szerepűek.

-   strukturált P2P: a csúcsok által kiadott gráfszerkezet rögzített
-   strukturálatlan P2P: a csúcsok szomszédai véletlenszerűek
-   hibrid P2P: néhány csúcsnak speciális szerepe van, ezek a többitol eltérő szervezésűek

### Overlay hálózat

overlay: A gráfban szomszédos csúcsok a fizikai hálózaton lehetnek távol egymástól, a rendszer elfedi, hogy a köztük levo kommunikáció több gépet érintve történik.

-   A legtöbb P2P rendszer overlay hálózatra épül

## Strukturált P2P rendszerek

A csúcsokat valamilyen struktúra szerint overlay hálózatba szervezzük (pl. logikai gyűrű), és a csúcsoktól az azonosítójuk alapján lehet szolgáltatásokat igénybe venni.

### Példa: d dimenziós particionált tér

Az adatoknak most d mezoje van, kulccsal nem rendelkeznek. Az így adódó tér fel van osztva annyi tartományra, ahány csúcsunk van; minden csúcs valamelyik tartomány adataiért felelos. Ha egy új csúcs érkezik, kettébontunk egy tartományt.

### Strukturálatlan P2P rendszer

A strukturálatlan P2P rendszerek igyekeznek véletlen gráfstruktúrát fenntartani.

-   Mindegyik csúcsnak csak részleges nézete van a gráfról (a teljes hálózatnak csak egy kis részét látja).
-   Minden P csúcs idoközönként kiválaszt egy szomszédos Q csúcsot
-   P és Q információt cserél, valamint átküldik egymásnak az általuk ismert csúcsokat

## Overlay hálózatok topológiájának kezelése

Különböztessünk meg két réteget: (1) az alsó rétegben a csúcsoknak csak részleges nézete van; (2) a felso rétegbe csak kevés csúcs kerülhet. Az alsó réteg véletlenszerű csúcsokat ad át a felso rétegnek; a felső réteg ezek közül csak keveset tart meg.

### Tórusz overlay topológia kialakítása

Ha megfeleloen választjuk meg, milyen csúcsokat tartson meg a felső réteg, akkor a kezdetben véletlenszerű overlay kapcsolatok hamarosan szabályos alakba rendezodnek. Itt egy távolságfüggvény szerinti megtartó szabály hat (az overlay a közelieket veszi át), és már az elso néhány lépés után jól látszik a kijövő tórusz-alakzat.

## Overlay topológia: példa: clusterezés

Most mindegyik i csúcshoz hozzárendelünk egy GID(i) ∈ N számot, és azt mondjuk, hogy i a GID(i) csoportba tartozik.

Szintén távolságfüggvényt használunk:

dist(i,j) =  1 ha GID(i) = GID(j)

0 ha GID(i) 6= GID(j)

Itt is igen gyorsan kialakul a kívánt szerkezet: csak az azonos csoportbeli csúcsok között lesz kapcsolat, kialakulnak a clusterek.

## Superpeer csúcsok

superpeer: olyan kisszámú csúcs, amelyeknek külön feladata van

## Hibrid arch.: kliens-szerver + P2P: edge szerver

edge szerver: az adatokat tároló szerver, a kliensekhez minél közelebb van elhelyezve, jellemzoen ott, ahol egy nagyobb hálózat az Internetre csatlakozik Content Delivery Network (CDN) rendszerekben jellemzo, a tartalomszolgáltatás hatékonyságát növelik és költségét csökkentik.

## Hibrid arch.: kliens-szerver + P2P: BitTorrent

Miután a csúcs kiderítette, melyik másik csúcsok tartalmaznak részeket a kívánt fájlból, azokat párhuzamosan tölti le, és egyúttal önmaga is kiajánlja megosztásra.

## Architektúrák és köztesréteg

Elofordulhat, hogy az elosztott rendszer/alkalmazás szerkezete nem felel meg a megváltozott igényeknek. Ilyenkor legtöbbször nem kell újraírni a teljes rendszert: elegendo lehet (dinamikusan) adaptálni a köztesréteg viselkedését.

### Interceptor

interceptor: Távoli objektum elérése során a vezérlés szokásos menetébe avatkozik bele, pl. átalakíthatja más formátumra a kérést. Jellemzoen az architektúra rétegei közé illeszthető.

## Adaptív middleware

-   Funkciók szétválasztása (separation of concerns): A szoftver különbözo jellegű funkciói váljanak minél jobban külön, így azokat könnyebb egymástól függetlenül módosítani.
-   Önvizsgálat (reflection): A program legyen képes feltárni a saját szerkezetét, és futás közben módosítani azt.
-   Komponensalapú szervezés: Az elosztott alkalmazás legyen moduláris, a komponensei legyenek könnyen cserélhetoek. A komponensek közötti függések legyenek egyértelműek, és csak annyi legyen belolük, amennyi feltétlenül szükséges

## Önszervezo elosztott rendszerek

### Adaptív rendszer képességei

Az egyes szoftverelemek adaptivitása kihat a rendszerre, ezért megvizsgáljuk, hogyan lehet adaptív rendszereket készíteni. Különféle elvárásaink lehetnek:

-   Önkonfiguráció
-   Önkezelo
-   Öngyógyító
-   Önoptimalizáló
-   Ön\*

## Adaptivitás visszacsatolással

### Visszacsatolásos modell

Az ön\* rendszerek sokszor az alábbi jellegű visszacsatolásos vezérléssel rendelkeznek: mérik, hogy a rendszer mennyire tér el a kívánt tulajdonságoktól, és szükség szerint változtatnak a beállításokon.

## Globule

-   Kollaboratív webes CDN, a tartalmakat költségmodell alapján helyezi el (minden szempontra: fontosság×költség).
-   A központi szerver (origin server) elemzi, ami történt, és az alapján állítja be a fontossági paramétereket, hogy mi történt volna, ha P oldalt az S edge szerver tárolta volna.
-   A számításokat különbözo stratégiákra végzi el, végül a legjobbat választja ki.

## Szálak: bevezetés

A legtöbb hardvereszköznek létezik szoftveres megfeleloje.

Processzor (CPU): Hardvereszköz, utasításokat képes sorban végrehajtani,
amelyek egy megadott utasításkészletbol származnak. 

Szál (thread): A processzor egyfajta szoftveres megfeleloje, minimális
kontextussal (környezettel). Ha a szálat megállítjuk, a kontextus
elmentheto és továbbfuttatáshoz visszatölthető.˝

Folyamat (process, task): Egy vagy több szálat összefogó nagyobb egység.
Egy folyamat szálai közös memóriaterületen (címtartományon)
dolgoznak, azonban különbözo folyamatok nem látják egymás
memóriaterületét.

## Kontextusváltás

-   kontextusváltás: A másik folyamatnak/szálnak történo˝
    vezérlésátadás, illetve a megfelelo kontextusok cseréje. Így egy
    processzor több folyamatot/szálat is végre tud hajtani.

-   Processzor kontextusa: Az utasítások végrehajtásában szerepet
    játszó kisszámú regiszter (elemi értéktároló) tartalma.

-   Szál kontextusa: Jellemzoen nem sokkal bővebb a
    processzorkontextusnál. A szálak közötti váltáshoz nem kell
    igénybe venni az operációs rendszer szolgáltatásait.

-   Folyamat kontextusa: Ahhoz, hogy a régi és az új folyamat
    memóriaterülete elkülönüljön, a memóriavezérlo (memory
    management unit, MMU) tartalmának jórészét át kell írni, amire
    csak a kernel szintnek van joga.
    A folyamatok létrehozása, törlése és a kontextusváltás köztük
    sokkal költségesebb a szálakénál.

## Szálak és operációs rendszerek

### Hol legyenek a szálak?

A szálakat kezelheti az operációs rendszer, vagy tole független
szálkönyvtárak. Mindkét megközelítésnek vannak elonyei és hátrányai.

### Szálak folyamaton belül: szálkönyvtárak

-   elony : Minden műveletet egyetlen folyamaton belül kezelünk, ez
    hatékony.
-   hátrány: Az operációs rendszer számára a szál minden művelete a
    gazdafolyamattól érkezik ⇒ ha a kernel blokkolja a szálat (pl.
    lemezművelet során), a folyamat is blokkolódik.
-   hátrány: Ha a kernel nem látja a szálakat közvetlenül, hogyan tud
    szignálokat közvetíteni nekik?

## Szálak és operációs rendszerek

### Szálak folyamaton kívül: kernelszintű szálak

A szálkönyvtárak helyezhetoek kernelszintre is. Ekkor minden szálművelet
rendszerhíváson keresztül érheto el.

-   elony : A szálak blokkolása nem okoz problémát: a kernel be tudja
    ütemezni a gazdafolyamat egy másik szálát.
-   elony : A szignálokat a kernel a megfelelo szálhoz tudja irányítani.
-   hátrány: Mivel minden művelet a kernelt érinti, ez a hatékonyság
    rovására megy.

## Solaris szálak

### Könnyűsúlyú folyamatok

könnyűsúlyú folyamat (lightweight process, LWP): Kernelszintű szálak,
amelyek felhasználói szintű szálkezeloket futtatnak.

## Szálak a kliensoldalon

### Többszálú webkliens

A hálózat késleltetésének elfedése:

-   A böngészo letöltött egy oldalt, ami több másik tartalomra hivatkozik.
-   Mindegyik tartalmat külön szálon tölti le, amíg a HTTP kéréseket
    kiszolgálják, ezek blokkolódnak.
-   Amikor egy-egy fájl megérkezik, a blokkolás megszűnik, és a böngészo˝
    megjeleníti a tartalmat.

### Több távoli eljáráshívás (RPC) egyszerre

-   Egy kliens több távoli szolgáltatást szeretne igénybe venni. Mindegyik
    kérést külön szál kezeli.
-   Megvárja, amíg mindegyik kérésre megérkezik a válasz.
-   Ha különbözo gépekre irányulnak a kérések, akár lineáris mértékű
    gyorsulás is elérheto így

## Szálak a szerveroldalon

### Cél: a hatékonyság növelése

-   Szálakat sokkal olcsóbb elindítani, mint folyamatokat (ido- és tárigény
    szempontjából egyaránt).
-   Mivel egy processzor csak egy szálat tud végrehajtani, a
    többprocesszoros rendszerek kapacitását csak többszálú szerverek
    képesek kihasználni.
-   A kliensekhez hasonlóan, a hálózat késleltetését lehet elfedni azzal, ha
    egyszerre több kérést dolgoz fel a szerver.

### Cél: a program szerkezetének javítása

-   A program jobban kezelheto lehet, ha sok egyszerű, blokkoló hívást
    alkalmaz, mint más szerkezet esetén. Ez némi teljesítményveszteséggel
    járhat.
-   A többszálú programok sokszor kisebbek és könnyebben érthetoek , mert
    jobban átlátható, merre halad a vezérlés.

## Virtualizáció

A virtualizáció szerepe egyre no több okból.

-   A hardver gyorsabban fejlodik a szoftvernél
-   Növeli a kód hordozhatóságát és költöztethetoségét
-   A hibás vagy megtámadott rendszereket könnyű így elkülöníteni

## A virtuális gépek szerkezete

### Virtualizálható komponensek

A rendszereknek sokfajta olyan rétege van, amely mentén virtualizálni
lehet a komponenseket. Mindig eldöntendo, milyen interfészeket kell
szolgáltatnia a virtuális gépnek (és milyeneket vehet igénybe).

## Process VM, VM monitor

-   Process VM: A virtuális gép (virtual machine, VM) közönséges
    programként fut, bájtkódot (elofordított programkódot) hajt végre.
    Pl. JVM, CLR, de vannak speciális célúak is, pl. ScummVM.
-   VM Monitor (VMM), hypervisor: Hardver teljeskörű virtualizációja,
    bármilyen program és operációs rendszer futtatására képes. Pl.
    VMware, VirtualBox.

## VM monitorok működése

Sok esetben a VMM egy operációs rendszeren belül fut.

-   A VMM a futtatott gépi kódú utasításokat átalakítja a gazdagép
    utasításaivá, és azokat hajtja végre.
-   A rendszerhívásokat és egyéb privilegizált utasításokat, amelyek
    végrehajtásához az operációs rendszer közreműködésére lenne
    szükség, megkülönböztetett módon kezeli.

## Kliens: átlátszóság

### A kliensekkel kapcsolatos fobb átlátszóságok

-   hozzáférési: az RPC kliensoldali csonkja
-   elhelyezési/áthelyezési: a kliensoldali szoftver tartja számon, hol
    helyezkedik el az eroforrás
-   többszörözési: a klienscsonk kezeli a többszörözött hívásokat
-   meghibásodási: sokszor csak a klienshez helyezheto – csak ott jelezhető˝
    a kommunikációs probléma

## Szerver: általános szerkezet

szerver: Olyan folyamat, amely egy (vagy akár több) porton várja a
kliensek kéréseit. Egy adott porton (ami egy 0 és 65535 közötti szám)
a szerver egyfajta szolgáltatást nyújt.
A 0-1023 portok közismert szolgáltatásokat nyújtanak, ezeket Unix
alapú rendszereken csak rendszergazdai jogosultsággal lehet foglalni.

### Szerverfajták

-   szuperszerver : Olyan szerver, amelyik több porton figyeli a bejövo˝
    kapcsolatokat, és amikor új kérés érkezik, új folyamatot/szálat indít
    annak kezelésére. Pl. Unix rendszerekben: inetd.
-   iteratív↔konkurens szerver : Az iteratív szerverek egyszerre csak egy
    kapcsolatot tudnak kezelni, a konkurensek párhuzamosan többet
    is.

## Szerver: sávon kívüli kommunikáció

### Sürgos üzenetek küldése

Meg lehet-e szakítani egy szerver működését kiszolgálás közben?

### Külön port

A szerver két portot használ, az egyik a sürgos üzeneteknek van fenntartva:

-   Ezt külön szál/folyamat kezeli
-   Amikor fontos üzenet érkezik, a normál üzenet fogadása szünetel
-   A szálnak/folyamatnak nagyobb prioritást kell kapnia, ehhez az
    oprendszer támogatása szükséges

### Sávon kívüli kommunikáció

Sávon kívüli kommunikáció használata, ha rendelkezésre áll:

-   Pl. a TCP protokoll az eredeti kérés kapcsolatán keresztül képes sürgos˝
    üzenetek továbbítására
-   Szignálok formájában kapható el a szerveren belül

## Szerver: állapot

### Állapot nélküli szerver

Nem tart fenn információkat a kliensrol a kapcsolat bontása után.

-   Nem tartja számon, melyik kliens milyen fájlból kért adatokat
-   Nem ígéri meg, hogy frissen tartja a kliens gyorsítótárát
-   Nem tartja számon a bejelentkezett klienseket: nincsen munkamenet
    (session)

#### Következmények

-   A kliensek és a szerverek teljesen függetlenek egymástól
-   Kevésbé valószínű, hogy inkonzisztencia lép fel azért, mert valamelyik
    oldal összeomlik
-   A hatékonyság rovására mehet, hogy a szerver nem tud semmit a
    kliensrol, pl. nem tudja előre betölteni azokat az adatokat, amelyekre a
    kliensnek szüksége lehet

### Állapotteljes szerverek

Állapotot tart számon a kliensekrol:

-   Megjegyzi, melyik fájlokat használta a kliens, és ezeket elore meg
    tudja nyitni legközelebb
-   Megjegyzi, milyen adatokat töltött le a kliens, és frissítéseket
    küldhet neki

#### Elonyök és hátrányok

Az állapotteljes szerverek nagyon hatékonyak tudnak lenni, ha a
kliensek lokálisan tárolhatnak adatokat.
Az állapotteljes rendszereket megfeleloen megbízhatóvá is lehet tenni
a hatékonyság jelentos rontása nélkül. 

## Szerver: háromrétegű clusterek

![](haromretegu.PNG)

## A kérések kezelése

### Szűk keresztmetszet

Ha minden kapcsolatot végig az elso réteg kezel, könnyen szűk
keresztmetszetté válhat.

A terhelés csökkentheto, ha a kapcsolatot átadjuk ( TCP handoff).

## Elosztott rendszerek: mobil IPv6

### Essence

A mobil IPv6-ot támogató kliensek az elosztott szolgáltatás bármelyik
peer-jéhez kapcsolódhatnak.

-   A C kliens kapcsolódik a szerver otthonának (home address, HA)
    IPv6 címéhez
-   A HA címen a szerver hazai ügynöke (home agent) fogadja a
    kérést, és a megfelelo˝ felügyeleti címre (care-of address, CA)
    továbbítja
-   Ezután C és CA már közvetlenül tudnak kommunikálni (HA
    érintése nélkül)

### kollaboratív CDN-ek

Az origin server tölti be HA szerepét, és átadja a beérkezo kapcsolatot
a megfelelo peer szervernek. A kliensek számára az origin és a peer
egy szervernek látszik.

## Kódmigráció: jellemzo feladatok

kódmigráció: olyan kommunikáció, amely során nem csak adatokat
küldünk át

Néhány jellemzo feladat, amelyhez kódmigrációra van szükség.

-   Client-Server: a szokásos kliens-szerver kommunikáció, nincsen
    kódmigráció
-   Remote Evaluation: a kliens feltölti a kódot, és a szerveren futtatja
-   Code on Demand: a kliens letölti a kódot a szerverrol, és helyben
    futtatja
-   Mobile Agent: a mobil ágens feltölti a kódját és az állapotát, és a
    szerveren folytatja a futását

## Kódmigráció: gyenge és eros mobilitás

### Objektumkomponensek

-   Kódszegmens: a programkódot tartalmazza
-   Adatszegmens: a futó program állapotát tartalmazza
-   Végrehajtási szegmens: a futtató szál környezetét tartalmazza

### Gyenge mobilitás

A kód- és adatszegmens mozgatása (a kód újraindul):

-   Viszonylag egyszerű megtenni, különösen, ha a kód hordozható
-   Irány szerint: feltöltés (push, ship), letöltés (pull, fetch)

### Eros mobilitás

A komponens a végrehajtási szegmenssel együtt költözik

-   Migráció: az objektum átköltözik az egyik géprol a másikra
-   Klónozás: a kód másolata kerül a másik gépre, és ugyanabból az
    állapotból indul el, mint az eredeti; az eredeti is fut tovább

## Kódmigráció: az eroforrások elérése

Az eredeti gépen található eroforrások költözés után a kód számára
távoliakká válnak.

### Eroforrás–gép kötés erőssége

-   Mozdíthatatlan: nem költöztetheto (pl. fizikai hardver)
-   Rögzített: költöztetheto, de csak drágán (pl. nagy adatbázis)
-   Csatolatlan: egyszerűen költöztetheto (pl. gyorsítótár)

### Komponens–eroforrás kötés jellege

Milyen jellegű eroforrásra van szüksége a komponensnek?

-   Azonosítókapcsolt: egy konkrét (pl. a cég adatbázisa)
-   Tartalomkapcsolt: adott tartalmú (pl. bizonyos elemeket
    tartalmazó cache)
-   Típuskapcsolt: adott jellegű (pl. színes nyomtató)

## Kódmigráció: az eroforrások elérése

### Kapcsolat az eroforrással

Hogyan tud a komponens kapcsolatban maradni az eroforrással?

-   Típuskapcsolt eroforrás esetén a legkönnyebb újrakapcsolódni
    egy lokális, megfelelo típusú erőforráshoz
-   Azonosítókapcsolt vagy tartalomkapcsolt esetben:
    -   rendszerszintű hivatkozást létesíthetünk az eredeti eroforrásra ,
        -   mozdíthatatlan eroforrások esetén ez az egyetlen lehetőség
        -   minden más esetben is szóba jöhet, de általában van jobb megoldás
    -   azonosítókapcsolt eroforrást érdemes áthelyezni
    -   tartalomkapcsolt eroforrást érdemes lemásolni

## Kódmigráció: heterogén rendszerben

### Nehézségek

-   A célgép nem biztos, hogy képes futtatni a migrált kódot
-   A processzor-, szál- és/vagy folyamatkörnyezet nagyban függ a
    lokális hardvertol, oprendszertől és futtatókörnyezettől

### Megoldás problémás esetekben

Virtuális gép használata: akár process VM, akár hypervisor.
Természetesen a virtuális gépnek elérhetonek kell lennie mindkét
környezetben.

# Kommunikacio

## Az ISO/OSI hálózatkezelési modell

![](osi.PNG)

### Hátrányok

-   Csak az üzenetküldésre koncentrál
-   Az (5) és (6) rétegek legtöbbször nem jelennek meg ilyen tisztán
-   Az elérési átlátszóság nem teljesül ebben a modellben

## Az alsó rétegek

### A rétegek feladatai

-   Fizikai réteg: a bitek átvitelének fizikai részleteit írja le
-   Adatkapcsolati réteg: az üzeneteket keretekre tagolja, célja a
    hibajavítás és a hálózat terhelésének korlátozása
-   Hálózati réteg: a hálózat távoli gépei között közvetít csomagokat
    útválasztás (routing) segítségével

## Szállítási réteg

### Absztrakciós alap

A legtöbb elosztott rendszer a szállítási réteg szolgáltatásaira épít.

### A legfobb protokollok

-   TCP: kapcsolatalapú, megbízható, sorrendhelyes átvitel
-   UDP: nem (teljesen) megbízható, általában kis üzenetek
    (datagram) átvitele

## Csoportcímzés

IP-alapú többcímű üzenetküldés (multicasting) sokszor elérheto, de
legfeljebb a lokális hálózaton belül használatos.

## Köztesréteg

### Szolgáltatásai

A köztesrétegbe (middleware) olyan szolgáltatásokat és protokollokat
szokás sorolni, amelyek sokfajta alkalmazáshoz lehetnek hasznosak.

-   Sokfajta kommunikációs protokoll
-   Sorosítás ((de)serialization, (un)marshalling), adatok
    reprezentációjának átalakítása (elküldésre vagy elmentésre)
-   Elnevezési protokollok az eroforrások megosztásának
    megkönnyítésére
-   Biztonsági protokollok a kommunikáció biztonságossá tételére
-   Skálázási mechanizmusok adatok replikációjára és
    gyorsítótárazására

### Alkalmazási réteg

Az alkalmazások készítoinek csak az alkalmazás-specifikus
protokollokat kell önmaguknak implementálniuk.

## A kommunikáció fajtái

-   idoleges (transient) vagy megtartó (persistent)
-   aszinkron vagy szinkron

### Idoleges vs megtartó

-   Megtartó kommunikáció: A kommunikációs rendszer hajlandó
    huzamosan tárolni az üzenetet.
-   Idoleges kommunikáció: A kommunikációs rendszer elveti az üzenetet,
    ha az nem kézbesítheto.

### A szinkronizáció lehetséges helyei

-   Az üzenet elindításakor
-   Az üzenet beérkezésekor
-   A kérés feldolgozása után

## Kliens–szerver modell

### Általános jellemzok˝

A kliens–szerver modell jellemzoen idoleges, szinkron kommunikációt
használ.

-   A kliensnek és a szervernek egyidoben kell aktívnak lennie.
-   A kliens blokkolódik, amíg a válasz meg nem érkezik.
-   A szerver csak a kliensek fogadásával foglalkozik, és a kérések
    kiszolgálásával.

### A szinkron kommunikáció hátrányai

-   A kliens nem tud tovább dolgozni, amíg a válasz meg nem érkezik
-   A hibákat rögtön kezelni kell, különben feltartjuk a klienst
-   Bizonyos feladatokhoz (pl. levelezés) nem jól illeszkedik

## Üzenetküldés

### Üzenetorientált köztesréteg (message-oriented middleware, MOM)

Megtartó, aszinkron kommunikációs architektúra.

-   Segítségével a folyamatok üzeneteket küldhetnek egymásnak
-   A küldo félnek nem kell válaszra várakoznia, foglalkozhat mással
-   A köztesréteg gyakran hibatűrést biztosít

## RPC: alapok

### Az RPC alapötlete

-   Az alprogramok használata természetes a fejlesztés során
-   Az alprogramok a jó esetben egymástól függetlenül működnek
    („fekete doboz”),
-   ... így akár egy távoli gépen is végrehajthatóak

## RPC: Hivas lepesei

![](rpc.PNG)

## RPC: paraméterátadás

### A paraméterek sorosítása

A második lépésben a klienscsonk elkészíti az üzenetet, ami az
egyszerű bemásolásnál összetettebb lehet.

-   A kliens- és a szervergépen eltérhet az adatábrázolás (eltéro˝
    bájtsorrend)
-   A sorosítás során bájtsorozat készül az értékbol
-   Rögzíteni kell a paraméterek kódolását:
    -   A primitív típusok reprezentációját (egész, tört, karakteres)
    -   Az összetett típusok reprezentációját (tömbök, egyéb
        adatszerkezetek)
-   A két csonknak fordítania kell a közös formátumról a gépeik
    formátumára

### RPC paraméterátadás szemantikája

-   Érték–eredmény szerinti paraméterátadási szemantika: pl. figyelembe
    kell venni, hogy ha (a kliensoldalon ugyanoda mutató) hivatkozásokat
    adunk át, azokról ez a hívott eljárásban nem látszik.
-   Minden feldolgozandó adat paraméterként kerül az eljáráshoz; nincsen
    globális hivatkozás.
    \###Átlátszóság
    Nem értheto el teljes mértékű elérési átlátszóság.

### Távoli hivatkozás

Távoli hivatkozás bevezetésével növelheto az elérési átlátszóságot:

-   A távoli adat egységesen érheto el
-   A távoli hivatkozásokat át lehet paraméterként adni

## Aszinkron RPC

### Az RPC „javítása”

A szerver nyugtázza az üzenet megérkezését. Választ nem vár

## Késleltetett szinkronizált RPC

### Késleltetett szinkronizált RPC

Ez két aszinkron RPC, egymással összehangolva.

## További lehetoség

A kliens elküldheti a kérését, majd idonként lekérdezheti a szervertől,
kész-e már a válasz.

## RPC: a kliens csatlakozása a szerverhez

### A kliens

-   A szolgáltatások katalógusba jegyzik be (globálisan és lokálisan
    is), melyik gépen érhetoek el. (1-2)
-   A kliens kikeresi a szolgáltatást a katalógusból. (3)
-   A kliens végpontot igényel a démontól a kommunikációhoz. (4)

## Üzenetorientált köztesréteg

### Működési elv

A köztesréteg várakozási sorokat (queue) tart fenn a rendszer gépein.
A kliensek az alábbi műveleteket használhatják a várakozási sorokra.

## Üzenetközvetíto˝

### Üzenetsorkezelo rendszer homogenitása

Az üzenetsorkezelo rendszerek feltételezik, hogy a rendszer minden
eleme közös protokollt használ, azaz az üzenetek szerkezete és
adatábrázolása megegyezo.˝

### Üzenetközvetíto˝

üzenetközvetíto (message broker) : Olyan központi komponens, amely
heterogén rendszerben gondoskodik a megfelelo konverziókról.

-   Átalakítja az üzeneteket a fogadó formátumára.
-   Szerepe szerint igen gyakran átjáró (application-level gateway,
    proxy) is, azaz a közvetítés mellet további (pl. biztonsági)
    funkciókat is nyújt
-   Az üzenetek tartalmát is megvizsgálhatják az útválasztáshoz
    (subject based vagy object based routing) ⇒ Enterprise
    Application Integration

## WebSphere MQ (IBM)

-   Az üzenetkezelok neve itt sorkezelő (queue manager); adott
    alkalmazásoknak címzett üzeneteket fogadnak
    -   Az üzenetkezelot össze lehet szerkeszteni a kliensprogrammal
    -   Az üzenetkezelo RPC-n keresztül is elérhető˝
-   Az útválasztótáblák (routing table) megadják, melyik kimeno˝
    csatornán kell továbbítani az üzenetet
-   A csatornákat üzenetcsatorna-ügynökök (message channel agent,
    MCA) kezelik
    -   Kiépítik a hálózati kapcsolatokat (pl. TCP/IP)
    -   Ki- és becsomagolják az üzeneteket, és fogadják/küldik a
        csomagokat a hálózatról

## Folyamatos média

### Az ido szerepe

Az eddig tárgyalt kommunikációfajtákban közös, hogy diszkrét
ábrázolásúak: az adategységek közötti idobeli kapcsolat nem
befolyásolja azok jelentését.

### Folyamatos ábrázolású média

A fentiekkel szemben itt a továbbított adatok idofüggőek .
Néhány jellemzo példa:

-   audio
-   videó
-   animációk
-   szenzorok adatai (homérséklet, nyomás stb.)

### Adatátviteli módok

Többfajta megkötést tehetünk a kommunikáció idobeliségével
kapcsolatban.

-   aszinkron: nem ad megkötést arra, hogy mikor kell átvinni az
    adatot
-   szinkron: az egyes adatcsomagoknak megadott idotartam alatt
    célba kell érniük
-   izokron vagy izoszinkrona
    : felso és alsó korlátot is ad a csomagok
    átvitelére; a remegés (jitter) így korlátozott mértékű

## Folyam

### Adatfolyam

adatfolyam: Izokron adatátvitelt támogató kommunikációs forma.

### Fontosabb jellemzok˝

-   Egyirányú
-   Legtöbbször egy forrástól (source) folyik egy vagy több nyelo˝
    (sink) felé
-   A forrás és/vagy a nyelo gyakran közvetlenül kapcsolódik
    hardverelemekhez (pl. kamera, képernyo)˝
-   egyszerű folyam: egyfajta adatot továbbít, pl. egy audiocsatornát
    vagy csak videót
-   összetett folyam: többfajta adatot továbbít, pl. sztereo audiót vagy
    hangot+videót

### Szolgáltatás minosége

A folyamokkal kapcsolatban sokfajta követelmény írható elo, ezeket
összefoglaló néven a szolgáltatás minoségének (Quality of Service,
QoS) nevezzük. Ilyen jellemzok a következők:

-   A folyam átvitelének „sebessége”: bit rate.
-   A folyam megindításának legnagyobb megengedett késleltetése.
-   A folyam adategységeinek megadott ido alatt el kell jutniuk a
    forrástól a nyeloig ( end-to-end delay), illetve számíthat az
    oda-vissza út is (round trip delay).
-   Az adategységek beérkezési idoközeinek egyenetlensége:
    remegés (jitter).

## Folyam: QoS biztosítása

### Differenciált szolgáltatási architektúra

Több hálózati eszköz érheto el, amelyekkel a QoS biztosítható.
Egy lehetoség, ha a hálózat routerei kategorizálják az áthaladó
forgalmat a beérkezo adatcsomagok tartalma szerint, és egyes
csomagfajtákat elsobbséggel továbbítanak ( differentiated services).

### A remegés csökkentése

A routerek pufferelhetik az adatokat a remegés csökkentésére.

## Összetett folyam szinkronizációja

### Szinkronizáció a nyelonél

Az összetett folyam alfolyamait szinkronizálni kell a nyelonél, különben
idoben elcsúszhatnának egymáshoz képest.

### Multiplexálás

Másik lehetoség: a forrás már eleve egyetlen folyamot készít
(multiplexálás). Ezek garantáltan szinkronban vannak egymással, a
nyelonél csak szét kellőket bontani ( demultiplexálás).

## Alkalmazásszintű multicasting

A hálózat minden csúcsának szeretnénk üzenetet tudjunk küldeni
(multicast). Ehhez hierarchikus overlay hálózatba szervezzük oket.

### Chord struktúrában tárolt fa készítése

-   A multicast hálózatunkhoz generálunk egy azonosítót, így
    egyszerre több multicast hálózatunk is lehet egy rendszerben.
-   Tegyük fel, hogy az azonosító egyértelműen kijelöl egy csúcsot a
    rendszerünkbena
    . Ez a csúcs lesz a fa gyökere.
-   Terv: a küldendo üzeneteket mindenki elküldi a gyökérhez, majd
    onnan a fán lefele terjednek.
-   Ha a P csúcs csatlakozni szeretne a multicast hálózathoz,
    csatlakozási kérést küld a gyökér felé. A P csúcstól a gyökérig
    egyértelmű az útvonalb
    ; ennek minden csúcsát a fa részévé
    teszünk (ha még nem volt az). Így P elérhetové válik a gyökértől.

## Alkalmazásszintű multicasting: költségek

-   Kapcsolatok terhelése: Mivel overlay hálózatot alkalmazunk,
    elofordulhat, hogy egy üzenetküldés többször is igénybe veszi
    ugyanazt a fizikai kapcsolatot.
    Példa: az A → D üzenetküldés kétszer halad át az Ra → Rb élen.
-   Stretch: Az overlayt követo és az alacsonyszintű üzenetküldés
    költségének hányadosa.
    Példa: B → C overlay költsége 71, hálózati 47 ⇒ stretch = 71/47.

## Járványalapú algoritmusok

### Alapötlet

-   Valamelyik szerveren frissítési műveletet (update) hajtottak végre,
    azt szeretnénk, hogy ez elterjedjen a rendszerben minden
    szerverhez.
-   Minden szerver elküldi a változást néhány szomszédjának
    (messze nem az összes csúcsnak) lusta módon (nem azonnal)
-   Tegyük fel, hogy nincs olvasás-írás konfliktus a rendszerben.

### Két alkategória

-   Anti-entrópia: Minden szerver rendszeresen kiválaszt egy másikat,
    és kicserélik egymás között a változásokat.
-   Pletykálás (gossiping): Az újonnan frissült (megfertozött ) szerver
    elküldi a frissítést néhány szomszédjának (megfertoziőket).

## Járvány: anti-entrópia

### A frissítések cseréje

-   P csúcs Q csúcsot választotta ki.
-   Küldés (push): P elküldi a nála levo frissítéseket Q-nak
-   Rendelés (pull): P bekéri a Q-nál levo frissítéseket
-   Küldés–rendelés (push–pull): P és Q kicserélik az adataikat, így
    ugyanaz lesz mindketto tartalma.

### Hatékonyság

A küldo–rendelő megközelítés esetébenő(log(N)) nagyságrendű
forduló megtétele után az összes csúcshoz eljut a frissítés.
Egy fordulónak az számít, ha mindegyik csúcs megtett egy lépést.

## Járvány: pletykálás

### Működési elv

Ha az S szerver új frissítést észlelt, akkor felveszi a kapcsolatot más
szerverekkel, és elküldi számukra a frissítést.
Ha olyan szerverhez kapcsolódik, ahol már jelen van a frissítés, akkor
1/k
valószínűséggel abbahagyja a frissítés terjesztését.

### Hatékonyság

Kelloen sok szerver esetén a tudatlanságban maradó szerverek
(akikhez nem jut el a frissítés) száma exponenciálisan csökken a k
valószínűség növekedésével, de ezzel az algoritmussal nem
garantálható, hogy minden szerverhez eljut a frissítés.

## Járvány: értékek törlése

### A törlési művelet nem terjesztheto˝

Ha egy adat törlésének műveletét is az eloz˝ oekhez hasonlóan
terjesztenénk a szerverek között, akkor a még terjedo frissítési
műveletek újra létrehoznák az adatot ott, ahová a törlés eljutott.

### Megoldás

A törlést speciális frissítésként: halotti bizonyítvány (death certificate)
küldésével terjesztjük.

### Halotti bizonyítvány törlése

A halotti bizonyítványt nem akarjuk örökké tárolni. Mikor törölhetoek?

-   Szemétgyűjtés-jellegű megközelítés: Egy rendszerszintű
    algoritmussal felismerjük, hogy mindenhová eljutott a bizonyítvány,
    és ekkor mindenhonnan eltávolítjuk. Ez a megoldás nem jól
    skálázódik.
-   elavuló bizonyítvány: Kibocsátás után adott idovel a bizonyítvány
    elavul, és ekkor törölheto; így viszont nem garantálható, hogy
    mindenhová elér.

# Elnevezési rendszerek

Az elosztott rendszerek entitásai a kapcsolódási pontjaikon (access
point) keresztül érhetoek el. Ezeket távolról a ˝ címük azonosítja, amely
megnevezi az adott pontot.
Célszer ˝u lehet az entitást a kapcsolódási pontjaitól függetlenül is
elnevezni. Az ilyen nevek helyfüggetlenek (location independent).
Az egyszer ˝u neveknek nincsen szerkezete, tartalmuk véletlen szöveg.
Az egyszer ˝u nevek csak összehasonlításra használhatóak.
### Azonosító
Egy név azonosító, ha egy-egy kapcsolatban áll a megnevezett
egyeddel, és ez a hozzárendelés maradandó, azaz a név nem
hivatkozhat más egyedre késobb sem. 


## Strukturálatlan nevek
### Strukturálatlan nevek feloldása
Milyen lehetoségek vannak strukturálatlan nevek feloldására? (Azaz: ˝
hogyan találjuk meg a hozzárendelt kapcsolódási pontot?)
- egyszer ˝u megoldások (broadcasting)
- otthonalapú megoldások
- elosztott hasítótáblák (strukturált P2P)
- hierarchikus rendszerek


## Névfeloldás: egyszer ˝u megoldások
### Broadcasting
Kihirdetjük az azonosítót a hálózaton; az egyed visszaküldi a jelenlegi címét.
- Lokális hálózatokon túl nem skálázódik
- A hálózaton minden gépnek figyelnie kell a beérkezo kérésre ˝
### Továbbítómutató
Amikor az egyed elköltözik, egy mutató marad utána az új helyére.
- A kliens elol el van fedve, hogy a szoftver továbbítómutató-láncot old fel. ˝
- A megtalált címet vissza lehet küldeni a klienshez, így a további
feloldások gyorsabban mennek.
- Földrajzi skálázási problémák
  - A hosszú láncok nem hibat ˝uroek ˝
  - A feloldás hosszú idobe telik ˝
  - Külön mechanizmus szükséges a láncok rövidítésére


## Otthonalapú megközelítések
### Egyréteg ˝u rendszer
Az egyedhez tartozik egy otthon, ez tartja számon az egyed jelenlegi
címét.
- Az egyed otthoni címe (home address) be van jegyezve egy
névszolgáltatásba
- Az otthon számon tartja az egyed jelenlegi címét (foreign address)
- A kliens az otthonhoz kapcsolódik, onnan kapja meg az aktuális
címet
### Kétréteg ˝u rendszer
Az egyes (pl. földrajzi alapon meghatározott) környékeken
feljegyezzük, hogy melyik egyedek tartózkodnak éppen arrafelé.
- A névfeloldás eloször ezt a jegyzéket vizsgálja meg ˝
- Ha az egyed nincsen a környéken, csak akkor kell az otthonhoz
fordulni

### Problémák
- Legalább az egyed élettartamán át fenn kell tartani az otthont
- Az otthon helye rögzített ⇒ költséges lehet, ha az egyed messze
költözik
- Rossz földrajzi skálázódás: az egyed sokkal közelebb lehet a
klienshez az otthonnál


## Eloszott hasítótábla
### Chord eloszott hasítótábla
Elosztott hasítótáblát (distributed hash table, DHT) készítünk
(konkrétan Chord protokoll szerintit), ebben csúcsok tárolnak
egyedeket. Az N csúcs gy ˝ur ˝u overlay szerkezetbe van szervezve.
- Mindegyik csúcshoz véletlenszer ˝uen hozzárendelünk egy m bites
azonosítót, és mindegyik entitáshoz egy m bites kulcsot. (Tehát
N ≤ 2
m.)
- A k kulcsú egyed felelose az az ˝ id azonosítójú csúcs, amelyre
k ≤ id, és nincsen köztük másik csúcs. A felelos csúcsot a kulcs ˝
rákövetkezojének ˝ is szokás nevezni; jelölje succ(k).
### Rosszul méretezod˝ o megoldás ˝
A csúcsok eltárolhatnák a gy ˝ur ˝u következo csúcsának elérhet ˝ oségét, ˝
és így lineárisan végigkereshetnénk a gy ˝ur ˝ut. Ez O(N) hatékonyságú,
rosszul skálázódik, nem hibat ˝uro...


## DHT: Finger table
### Chord alapú adattárolás
Mindegyik *p* csúcs egy FT<sub>p</sub> „finger table”-t tárol *m* bejegyzéssel:

<p style="text-align: center;">FT<sub>p</sub>[i] = succ(p +2<sup>i−1</sup>)</p>

Bináris (jelleg ˝u) keresést szeretnénk elérni, ezért minden lépés
felezi a keresési tartományt: 2<sup>m−1</sup> 2<sup>m−2</sup>,...,2<sup>0</sup>.
A *k* kulcsú egyed kikereséséhez (ha nem a jelenlegi csúcs
tartalmazza) a kérést továbbítjuk a *j* index ˝u csúcshoz, amelyre

<p style="text-align: center;">FT<sub>p</sub>[j] ≤ k < FT<sub>p</sub>[j +1]</p>

illetve, ha *p* < *k* < FT<sub>p</sub>[1], akkor is FT<sub>p</sub>[1]-hez irányítjuk a kérést.

### Jól méretezod˝ o megoldás ˝
Ez a megoldás O(m), azaz O(log(N)) hatékonyságú.



## A hálózati közelség kihasználása
### Probléma
Mivel overlay hálózatot használunk, az üzenetek sokat utazhatnak két csúcs
között: a k és a succ(k +1) csúcs messze lehetnek egymástól.

Azonosító topológia szerinti megválasztása: A csúcsok azonosítóját
megpróbálhatjuk topológiailag közeli csúcsokhoz közelinek választani.
Ez nehéz feladat lehet.

Közelség szerinti útválasztás: A p csúcs FTp táblája m elemet tartalmaz. Ha
ennél több információt is eltárolunk p-ben, akkor egy lépés megtételével
közelebb juthatunk a célcsúcshoz.

Szomszéd közelség szerinti megválasztása: Ha a Chordtól eltéro ábrázolást ˝
követünk, a csúcs szomszédainak megválasztásánál azok közelségét is
figyelembe lehet venni.

## Hierarchikus módszerek
### Hierarchical Location Services (HLS)
A hálózatot osszuk fel tartományokra, és mindegyik tartományhoz
tartozzon egy katalógus. Építsünk hierarchiát a katalógusokból.

## HLS: Katalógus-csúcsok
### A csúcsokban tárolt adatok
- Az E egyed címe egy levélben található meg
- A gyökértol az ˝ E leveléig vezeto úton minden bels ˝ o csúcsban van egy ˝
mutató a lefelé következo csúcsra az úton ˝
- Mivel a gyökér minden út kiindulópontja, minden egyedrol van ˝
információja

## HLS: Keresés a fában
### Keresés a fában
- A kliens valamelyik tartományba tartozik, innen indul a keresés
- Felmegyünk a fában addig, amíg olyan csúcshoz nem érünk, amelyik tud
E-rol, aztán követjük a mutatókat a levélig, ahol megvan ˝ E címe
- Mivel a gyökér minden egyedet ismer, az algoritmus terminálása
garantált

## HLS: Beszúrás
### Beszúrás a fában
- Ugyanaddig megyünk felfelé a fában, mint keresésnél
- Az érintett belso csúcsokba mutatókat helyezünk ˝
- Egy csúcsban egy egyedhez több mutató is tartozhat



## Névtér

névtér: gyökeres, irányított, élcímkézett gráf, a levelek tartalmazzák a
megnevezett egyedeket, a belso csúcsokat ˝ katalógusnak vagy könyvtárnak
(directory) nevezzük
Az egyedhez vezeto út címkéit összeolvasva kapjuk az egyed egy nevét. A ˝
bejárt út, ha a gyökérbol indul, ˝ abszolút útvonalnév, ha máshonnan, relatív
útvonalnév. Mivel egy egyedhez több út is vezethet, több neve is lehet.



### Attribútumok
A csúcsokban (akár a levelekben, akár a belso csúcsokban) különféle ˝
attribútumokat is eltárolhatunk.
- Az egyed típusát
- Az egyed azonosítóját
- Az egyed helyét/címét
- Az egyed más neveit


## Névfeloldás
### Gyökér szükséges
Kiinduló csúcsra van szükségünk ahhoz, hogy megkezdhessük a névfeloldást.
### Gyökér megkeresése
A név jellegétol függ ˝ o környezet biztosítja a gyökér elérhet ˝ oségét. Néhány ˝
példa név esetén a hozzá tartozó környezet:
- www.inf.elte.hu: egy DNS névszerver
- /home/steen/mbox: a lokális NFS fájlszerver
- 0031204447784: a telefonos hálózat
- 157.181.161.79: a www.inf.elte.hu webszerverhez vezeto út


## Csatolás (linking)
### Soft link
A gráf csúcsai valódi csatolások (hard link), ezek adják a névfeloldás
alapját.
soft link: a levelek más csúcsok álneveit is tartalmazhatják. Amikor a
névfeloldás ilyen csúcshoz ér, az algoritmus az álnév feloldásával
folytatódik.



## A névtér implementációja
### Nagyméret ˝u névtér tagolása
Ha nagy (világméret ˝u) névterünk van, el kell osztanunk a gráfot a gépek
között, hogy hatékonnyá tegyük a névfeloldást és a névtér kezelését.
Ilyen nagy névteret alkot a DNS (Domain Name System).
- Globális szint: Gyökér és felso csúcsok. A szervezetek közösen kezelik. ˝
- Szervezeti szint: Egy-egy szervezet által kezelt csúcsok szintje.
- Kezeloi szint: ˝ Egy adott szervezeten belül kezelt csúcsok.


|Szempont              | Globális       | Szervezeti         | Kezeloi             |
|---|---|---|---|
|Földrajzi méret       | Világméretű    | Vállalati          | Vállalati alegység  |
|Csúcsok száma         | Kevés          | Sok                | Rendkívül sok       |
|Keresés ideje         | mp.            | ezredmp.           | Azonnal             |
|Frissítés terjedése   | Ráéros         | Azonnal            | Azonnal             |
|Másolatok száma       | Sok            | Nincs/kevés        | Nincs               |
|Kliens gyorsítótáraz? | Igen           | Igen               | Néha                |



## A névtér implementációja: DNS
### A DNS egy csúcsában tárolt adatok
Legtöbbször az A rekord tartalmát kérdezzük le; a névfeloldáshoz
feltétlenül szükséges az NS rekord.
Egy zóna a DNS-fa egy összefüggo, adminisztratív egységként kezelt ˝
része, egy (ritkábban több) tartomány (domain) adatait tartalmazza.


## Iteratív névfeloldás
A névfeloldást a gyökér névszerverek egyikétol indítjuk. ˝
Az iteratív névfeloldás során a névnek mindig csak egy komponensét
oldjuk fel, a megszólított névszerver az ehhez tartozó névszerver címét
küldi vissza.

## Rekurzív névfeloldás
A rekurzív névfeloldás során a névszerverek egymás közt
kommunikálva oldják fel a nevet, a kliensoldali névfeloldóhoz rögtön a
válasz érkezik.

## Rekurzív névfeloldás: cache-elés

*TODO: Konvertalni tablazatba*

![](rekurziv_nevfeloldas.PNG)

## Névfeloldás: átméretezhetoség ˝
### Méret szerinti átméretezhetoség ˝
Sok kérést kell kezelni rövid ido alatt ˝ ⇒ a globális szint szerverei nagy
terhelést kapnának.
### Csúcsok adatai sok névszerveren
A felso két szinten, és sokszor még az alsó szinten is ritkán változik a ˝
gráf. Ezért megtehetjük, hogy a legtöbb csúcs adatairól sok
névszerveren készítünk másolatot, így a keresést várhatóan sokkal
közelebbrol indítjuk. ˝
### A keresett adat: az entitás címe
A legtöbbször a névfeloldással az entitás címét keressük.
A névszerverek nem alkalmasak mozgó entitások címeinek
kezelésére, mert azok költözésével gyakran változna a gráf.

## Névfeloldás: átméretezhetoség ˝
### Földrajzi átméretezhetoség ˝
A névfeloldásnál a földrajzi távolságokat is figyelembe kell venni.

### Helyfüggés
Ha egy csúcsot egy adott névszerver szolgál ki, akkor földrajzilag oda kell
kapcsolódnunk, ha el akarjuk érni a csúcsot.


## Attribútumalapú nevek
### Attribútumalapú keresés
Az egyedeket sokszor kényelmes lehet a tulajdonságaik (attribútumaik)
alapján keresni.
### Teljes általánosságban: nem hatékony
Ha bármilyen kombinációban megadhatunk attribútumértékeket, a
kereséshez az összes egyedet érintenünk kell, ami nem hatékony.
### X.500, LDAP
A katalógusszolgáltatásokban (directory service) az attribútumokra
megkötések érvényesek. A legismertebb ilyen szabvány az X.500,
amelyet az LDAP protokollon keresztül szokás elérni.
Az elnevezési rendszer fastruktúrájú, élei névalkotó jellemzokkel ˝
(attribútum-érték párokkal) címzettek. Az egyedekre az útjuk jellemzoi ˝
vonatkoznak, és további párokat is tartalmazhatnak.

# Szinkronizáció

## Fizikai órák
### Milyen módon van szükségünk az idore? ˝
Néha a pontos idot szeretnénk tudni, néha elég, ha megállapítható két ˝
idopont közül, melyik volt korábban. Foglalkozzunk el ˝ oször az els ˝ o kérdéssel. ˝
### Egyezményes koordinált világido˝
Az idoegységeket (pl. másodperc) az atomid ˝ ob˝ ol (TAI) származtatjuk. ˝
- Az atomido definíciója a gerjesztett céziumatom által kibocsátott ˝
sugárzás frekvenciáján alapul.
- A Föld forgásának sebessége kissé változékony, ezért a világido (UTC) ˝
néhány (szöko)másodperccel eltér az atomid ˝ ot˝ ol. ˝
- Az atomido˝ kb. 420 atomóra átlagából adódik.
Az atomórák pontosságának nagyságrendje kb. 1ns/nap.
- Az atomidot m ˝uholdak sugározzák, a vétel pontossága 0 ˝ .5 ms
nagyságrend ˝u, pl. az idojárás befolyásolhatja.


### Fizikai ido elterjesztése ˝
Ha a rendszerünkben van UTC-vevo, az megkapja a pontos id ˝ ot. Ezt a ˝
következok figyelembe vételével terjeszthetjük el a rendszeren belül. ˝
- A p gép saját órája szerint az ido˝ t UTC-idopillanatban ˝ C<sub>p</sub>(t).
- Ideális esetben mindig pontos az ido:˝ C<sub>p</sub>(t) = t, másképpen dC/dt = 1.


### Idoszinkronizáció üteme ˝
A valóságban p vagy túl gyors, vagy túl
lassú, de viszonylag pontos:

1 − ρ ≤(dC) / (dt) ≤ 1 + ρ
Ha csak megadott δ eltérést akarunk
megengedni, δ/(2ρ) másodpercenként
szinkronizálnunk kell az idot. 

## Óraszinkronizálás
### Cristian-algoritmus
Mindegyik gép egy központi idoszervert ˝ ol ˝ kéri le a pontos idot˝
legfeljebb δ/(2ρ) másodpercenként (Network Time Protocol).
- Nem a megkapott idore kell állítani az órát: bele kell számítani, ˝
hogy a szerver kezelte a kérést és a válasznak vissza kellett
érkeznie a hálózaton keresztül.


### Berkeley-algoritmus
Itt nem feltétlenül a pontos ido beállítása a cél, csak az, hogy minden ˝
gép ideje azonos legyen.
Az idoszerver néha bekéri mindegyik gép idejét, ebb ˝ ol átlagot von, ˝
majd mindenkit értesít, hogy a saját óráját mennyivel kell átállítania.
- Az ido egyik gépnél sem folyhat visszafelé: ha vissza kellene ˝
állítani valamelyik órát, akkor ehelyett a számontartott ido mérését ˝
lelassítja a gép mindaddig, amíg a kívánt ido be nem áll.

## Az elobb-történt reláció ˝

### Az elobb-történt (happened-before) reláció ˝
Az elobb-történt reláció ˝ az alábbi tulajdonságokkal rendelkezo reláció. ˝
Annak a jelölése, hogy az a esemény elobb-történt-mint ˝ b-t: a → b.
- Ha ugyanabban a folyamatban az a esemény korábban
következett be b eseménynél, akkor a → b.
- Ha a esemény egy üzenet küldése, és b esemény annak
fogadása, akkor a → b.
- A reláció tranzitív: ha a → b és b → c, akkor a → c

### Parcialitás
A fenti reláció parciális rendezés: elofordulhat, hogy két esemény közül ˝
egyik sem elozi meg a másikat.

## Logikai órák
### Az ido és az el ˝ obb-történt reláció ˝
Minden e eseményhez idobélyeget rendelünk, ami egy egész szám (jelölése: ˝
C(e)), és megköveteljük az alábbi tulajdonságokat.
- Ha a → b egy folyamat két eseményére, akkor C(a) < C(b).
- Ha a esemény egy üzenet küldése és b esemény annak fogadása, akkor
C(a) < C(b).


### Globális óra nélkül?
Ha a rendszerben van globális óra, azzal a fenti idobélyegek elkészíthet ˝ ok. ˝
A továbbiakban azt vizsgáljuk, hogyan lehet az idobélyegeket globális óra ˝
nélkül elkészíteni.


## Logikai órák: Lamport-féle idobélyegek ˝
Minden P<sub>i</sub>
folyamat saját C<sub>i</sub> számlálót tart nyilván az alábbiak szerint:
Pi minden eseménye eggyel növeli a számlálót.
Az elküldött m üzenetre ráírjuk az idobélyeget: ˝ ts(m) = C<sub>i</sub>
.
Ha az *m* üzenet beérkezik P<sub>j</sub>
folyamathoz, ott a számláló új értéke
C<sub>j</sub> = max{C<sub>j</sub>
,ts(m)}+1 lesz; így az ido „nem folyik visszafelé”. ˝
P<sub>i</sub> és P<sub>j</sub> egybeeso id ˝ obélyegei közül tekintsük a ˝ Pi
-belit elsonek, ha ˝ *i* < *j*.

### Beállítás: köztesréteg
Az órák állítását és az üzenetek idobélyegeit a köztesréteg kezeli.


## Logikai órák: példa
### Pontosan sorbarendezett csoportcímzés
Ha replikált adatbázison konkurens m ˝uveleteket kell végezni, sokszor
követelmény, hogy mindegyik másolaton ugyanolyan sorrendben hajtódjanak
végre a m ˝uveletek.
Az alábbi példában két másolatunk van, a számlán kezdetben $1000 van.
P<sub>1</sub> befizet $100-t, P<sub>2</sub> 1% kamatot helyez el.


### Probléma
Ha a m ˝uveletek szinkronizációja nem megfelelo, érvénytelen eredményt ˝
kapunk: másolat1 ← $1111, de másolat2 ← $1110.


## Példa: Pontosan sorbarendezett csoportcímzés
### Pontosan sorbarendezett csoportcímzés
A P<sub>i</sub>
folyamat minden m ˝uveletet idobélyeggel ellátott üzenetben küld el. ˝
Pi egyúttal beteszi a küldött üzenetet a saját queuei prioritásos sorába.
A P<sub>j</sub>
folyamat a beérkezo üzeneteket az ˝ o˝ queuej sorába teszi be az
idobélyegnek megfelel ˝ o prioritással. Az üzenet érkezésér ˝ ol mindegyik ˝
folyamatot értesíti.


P<sub>j</sub> akkor adja át a msgi üzenetet feldolgozásra, ha:
(1) msg<sub>i</sub> a queue<sub>j</sub> elején található (azaz az o id ˝ obélyege a legkisebb) ˝
(2) a queue<sub>j</sub> sorban minden P<sub>k</sub> (k 6= i) folyamatnak megtalálható legalább
egy üzenete, amelynek msgi
-nél késobbi az id ˝ obélyege ˝
### Feltételek
Feltételezzük, hogy a kommunikáció a folyamatok között megbízható és FIFO
sorrend ˝u.


## Idobélyeg-vektor ˝
### Okság
Arra is szükségünk lehet, hogy megállapíthassuk két eseményrol, hogy az ˝
egyik okoz(hat)ta-e a másikat – illetve fordítva, függetlenek-e egymástól.
Az eddigi megközelítésünk erre nem alkalmas: abból, hogy C(a) < C(b), nem
vonható le az a következtetés, hogy az a esemény okságilag megelozi ˝ a b
eseményt.

### A példában szereplo adatok ˝
a esemény: m1 beérkezett T = 16
idobélyeggel; ˝
b esemény: m2 elindult T = 20
idobélyeggel. ˝
Bár 16 < 20, a és b nem függenek
össze okságilag.

## Idobélyeg-vektor ˝
A P<sub>i</sub> most már az összes másik folyamat idejét is számon tartja egy
VCi
[1..n] tömbben, ahol VC<sub>i</sub>
[j] azoknak a P<sub>j</sub>
folyamatban bekövetkezett
eseményeknek a száma, amelyekrol ˝ P<sub>i</sub>
tud.
Az m üzenet elküldése során P<sub>i</sub> megnöveli eggyel VC<sub>i</sub>
[i] értékét (vagyis
az üzenetküldés egy eseménynek számít), és a teljes V<sub>i</sub>
idobélyeg-vektort ráírja az üzenetre. ˝
Amikor az m üzenet megérkezik a P<sub>j</sub>
folyamathoz, amelyre a ts(m)
idobélyeg van írva, két dolog történik: ˝
(1) VC<sub>j</sub>
[k] := max{VC<sub>j</sub>
[k],ts(m)[k]}
(2) VC<sub>j</sub>
[j] megno eggyel, vagyis az üzenet fogadása is egy eseménynek ˝
számít


## Pontosan sorbarendezett csoportcímzés
### Idobélyeg-vektor alkalmazása ˝
Az idobélyeg-vektorokkal megvalósítható a pontosan sorbarendezett ˝
csoportcímzés: csak akkor kézbesítjük az üzeneteket, ha már
mindegyik elozményüket kézbesítettük. ˝
Ehhez annyit változtatunk az elobb leírt id ˝ obélyeg-vektorok ˝
m ˝uködésén, hogy amikor P<sub>j</sub>
fogad egy üzenetet, akkor nem növeljük
meg VC<sub>j</sub>
[j] értékét.
Pj csak akkor kézbesíti az m üzenetet, amikor:
ts(m)[i] = VC<sub>j</sub>
[i] +1, azaz a P<sub>j</sub>
folyamatban P<sub>i</sub> minden korábbi
üzenetét kézbesítettük
ts(m)[k] ≤ VC<sub>j</sub>
[k] for k 6= i, azaz az üzenet „nem a jövob˝ ol jött”

## Kölcsönös kizárás
### Kölcsönös kizárás: a feladat
Több folyamat egyszerre szeretne hozzáférni egy adott eroforráshoz. ˝
Ezt egyszerre csak egynek engedhetjük meg közülük, különben az
eroforrás helytelen állapotba kerülhet. ˝
### Megoldásfajták
- Központi szerver használata.
- Peer-to-peer rendszeren alapuló teljesen elosztott megoldás.
- Teljesen elosztott megoldás általános gráfszerkezetre.
- Teljesen elosztott megoldás (logikai) gy ˝ur ˝uben.


## Kölcsönös kizárás: központosított

![](kolcsonos.PNG)

## Kölcsönös kizárás: decentralizált
Tegyük fel, hogy az eroforrás ˝ n-szeresen többszörözött, és minden
replikátumhoz tartozik egy azt kezelo koordinátor. ˝
Az eroforráshoz való hozzáférésr ˝ ol ˝ többségi szavazás dönt: legalább
m koordinátor engedélye szükséges, ahol m > n/2.
Feltesszük, hogy egy esetleges összeomlás után a koordinátor hamar
felépül – azonban a kiadott engedélyeket elfelejti.
Példa: hatékonyság
Tegyük fel, hogy a koordinátorok rendelkezésre állásának
valószín ˝usége 99.9% („három kilences”), 32-szeresen replikált az
eroforrásunk, és a koordinátorok háromnegyedének engedélyére van ˝
szükségünk (m = 0.75n).
Ekkor annak a valószín ˝usége, hogy túl sok koordinátor omlik össze,
igen alacsony: kevesebb mint 10<sup>−40</sup>
.


## Kölcsönös kizárás: elosztott
### M ˝uködési elv
Ismét többszörözött az eroforrás, amikor a kliens hozzá szeretne férni, ˝
kérést küld mindegyik koordinátornak (idobélyeggel). ˝
Választ (hozzáférési engedélyt) akkor kap, ha
- a koordinátor nem igényli az eroforást, vagy ˝
- a koordinátor is igényli az eroforrást, de kisebb az id ˝ obélyege. ˝
- Különben a koordinátor (átmenetileg) nem válaszol.


## Kölcsönös kizárás: zsetongy ˝ur ˝u
### Essence
A folyamatokat logikai gy ˝ur ˝ube szervezzük (fizikailag lehetnek pl. egy
lokális hálózaton).
A gy ˝ur ˝uben egy zsetont küldünk körbe, amelyik folyamat birtokolja, az
férhet hozzá az eroforráshoz.

| Algoritmus | Be+kilépési üzenetszám | Belépés elotti késleltetés | Problémák |
| --- | --- | --- | --- |
| Központosított | 3 | 2 | Ha összeomlik a koordinátor |
|Decentralizált | 2mk + m | 2mk | Kiéheztetés, rossz hatékonyság |
Elosztott | 2 (n – 1) | 2 (n – 1) | Bármely folyamat összeomlása |
| Zsetongy ˝ur ˝u | 1 .. ∞  | 0 .. n – 1 | A zseton elvész, a birtokló folyamat összeomlik |

## Csúcsok globális pozícionálása
### Feladat
Meg szeretnénk becsülni a csúcsok közötti kommunikációs
költségeket. Erre többek között azért van szükség, hogy hatékonyan
tudjuk megválasztani, melyik gépekre helyezzünk replikátumokat az
adatainkból.
### Ábrázolás
A csúcsokat egy többdimenziós geometriai térben ábrázoljuk, ahol a P
és Q csúcsok közötti kommunikációs költséget a csúcsok távolsága
jelöli. Így a feladatot visszavezettük távolságok becslésére.

A tér dimenziószáma minél nagyobb, annál pontosabb lesz a
becslésünk, de annál költségesebb is

## A pozíció kiszámítása
### A becsléshez szükséges csúcsok száma
Egy pont pozíciója meghatározható a tér dimenziószámánál eggyel
nagyobb számú másik pontból a tolük vett távolságok alapján.


### Nehézségek
- a késleltetések mért értékei
ingadoznak
- nem egyszer ˝uen összeadódnak a
távolságok −→

### Megoldás
Válasszunk L darab csúcsot, amelyek pozícióját tegyük fel, hogy nagyon
pontosan meghatároztuk.

Egy P csúcsot ezekhez viszonyítva helyezünk el: megmérjük az összestol ˝
mért késleltetését, majd úgy választjuk meg P pozícióját, hogy az össz-hiba
(a mért késleltetések és a megválasztott pozícióból geometriailag adódó
késleltetés eltérése) a legkisebb legyen.


## Vezetoválasztás: zsarnok-algoritmus ˝
### Vezetoválasztás: feladat ˝
Sok algoritmusnak szüksége van arra, hogy kijelöljön egy folyamatot,
amely aztán a további lépéseket koordinálja.
Ezt a folyamatot dinamikusan szeretnénk kiválasztani.
### Zsarnok-algoritmus
A folyamatoknak sorszámot adunk. A legnagyobb sorszámú folyamatot
szeretnénk vezetonek választani. ˝
- Bármelyik folyamat kezdeményezhet vezetoválasztást. Mindegyik ˝
folyamatnak (amelyrol nem ismert, hogy kisebb lenne a küld ˝ onél a ˝
sorszáma) elküld egy választási üzenetet.
- Ha P<sub>nagyobb</sub> üzenetet kap P<sub>kisebb</sub>-tol, visszaküld neki egy olyan ˝
üzenetet, amellyel kiveszi P<sub>kisebb</sub>-et a választásból.
- Ha P megadott idon belül nem kap letiltó üzenetet, ˝ o lesz a ˝
vezeto. Err ˝ ol mindegyik másik folyamatot értesíti egy üzenettel.



## Vezetoválasztás gy ˝ur ˝uben ˝

Ismét logikai gy ˝ur ˝unk van, és a folyamatoknak vannak sorszámai. A
legnagyobb sorszámú folyamatot szeretnénk vezetonek választani. ˝

Bármelyik folyamat kezdeményezhet vezetoválasztást: elindít egy ˝
üzenetet a gy ˝ur ˝un körbe, amelyre mindenki ráírja a sorszámát. Ha egy
folyamat összeomlott, az kimarad az üzenetküldés menetébol. ˝

Amikor az üzenet visszajut a kezdeményezohöz, minden aktív folyamat ˝
sorszáma szerepel rajta. Ezek közül a legnagyobb lesz a vezeto; ezt ˝
egy másik üzenet körbeküldése tudatja mindenkivel.

Nem okozhat problémát, ha több folyamat is egyszerre kezdeményez
választást, mert ugyanaz az eredmény adódik. Ha pedig az üzenetek
valahol elvesznének (összeomlik az éppen oket tároló folyamat), akkor ˝
újrakezdheto a választás.

## Superpeer-választás
### Szempontok
A superpeer-eket úgy szeretnénk megválasztani, hogy teljesüljön rájuk:
- A többi csúcs alacsony késleltetéssel éri el oket ˝
- Egyenletesen vannak elosztva a hálózaton
- A csúcsok megadott hányadát választjuk superpeer-nek
- Egy superpeer korlátozott számú peer-t szolgál ki


### Megvalósítás DHT használata esetén
Az azonosítók terének egy részét fenntartjuk a superpeer-ek számára.
Példa: ha m-bites azonosítókat használunk, és S superpeer-re van
szükségünk, a k = log<sub>2</sub>S felső egész része felso bitet foglaljuk le a superpeer-ek számára. ˝
Így N csúcs esetén kb. 2k−mN darab superpeer lesz.

A p kulcshoz tartozó superpeer: a p AND 11···11 00···00 kulcs felelose az








