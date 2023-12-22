/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 5
 * Teema: AVL-puud
 * Autor: Oliver Puusalu
 *
 * Mõningane eeskuju:
 *
 *****************************************************************************/

public class Kodu5 {

    /**
     *  Tipuga "juur" algavasse AVL-puusse etteantud võtmeväärtusega kirje lisamiseks (koos tasakaalustamisega)
     * @param juur
     * @param väärtus
     * @return tagastab juurtipu
     */
    public static KOTipp lisaKirje(KOTipp juur, int väärtus) {
        juur = lisaKirjeAbi(juur, väärtus);
        while (!onAVL(juur)) {
            juur = tasakaalusta(juur);
        }
        return juur;
    }

    /**
     * abimeetod kirje lisamiseks, ilma tasakaalustamiseta
     * @param juur
     * @param väärtus
     * @return tagastab tasakaalustamata puu juurtipu
     */
    static KOTipp lisaKirjeAbi(KOTipp juur, int väärtus) {
        if (juur == null){
            return new KOTipp(väärtus);
        } else {
            if (väärtus >= juur.väärtus){
                if (juur.p == null)
                    juur.p = new KOTipp(väärtus);
                else {
                    lisaKirjeAbi(juur.p, väärtus);
                }
            }
            else {
                if (juur.v == null)
                    juur.v = new KOTipp(väärtus);
                else {
                    lisaKirjeAbi(juur.v, väärtus);
                }
            }
        }
        return juur;
    }

    /**
     * Tipuga "juur" algavast AVL-puust etteantud võtmeväärtusega kirje eemaldamiseks (koos tasakaalustamisega)
     * @param juur
     * @param väärtus
     * @return tagastab juurtipu
     */
    public static KOTipp eemaldaKirje(KOTipp juur, int väärtus) {
        juur = eemaldaKirjeAbi(juur, väärtus);
        while (!onAVL(juur)) {
            juur = tasakaalusta(juur);
        }
        return juur;
    }

    /**
     * Abimeetod kirje eemaldamiseks, tagastab tasakaalustamata puu
     * @param juur
     * @param väärtus
     * @return tagastab tasakaalustamata puu juurtipu
     */
    private static KOTipp eemaldaKirjeAbi(KOTipp juur, int väärtus) {
        if (juur == null)
            return juur;
        if (väärtus < juur.väärtus)
            juur.v = eemaldaKirjeAbi(juur.v, väärtus);
        else if (väärtus > juur.väärtus)
            juur.p = eemaldaKirjeAbi(juur.p, väärtus);
        else {
            if (juur.v == null || juur.p == null) {
                KOTipp temp;
                if (juur.v != null)
                    temp = juur.v;
                else
                    temp = juur.p;

                if (temp == null) {
                    juur = null;
                }
                else
                    juur = temp;
            }
            else {
                KOTipp temp = minVäärtusKirje(juur.p);
                juur.väärtus = temp.väärtus;
                juur.p = eemaldaKirjeAbi(juur.p, temp.väärtus);
            }
        }
        return juur;
    }

    /**
     * Abimeetod mis leiab minimaalse väärtusega kirje AVL puus
     * @param tipp
     * @return tagastab minimaalse tipu
     */
    private static KOTipp minVäärtusKirje(KOTipp tipp) {
        KOTipp praegune = tipp;
        while (praegune.v != null)
            praegune = praegune.v;

        return praegune;
    }


    /**
     * Kahe AVL-puu kirjetest uue AVL-puu loomiseks, mis sisaldab mõlema AVL-puu kirjed
     * @param avl1
     * @param avl2
     * @return tagastab uue puu juurtipu
     */
    public static KOTipp liidaAVLpuud(KOTipp avl1, KOTipp avl2) {
        if (avl1 != null) {
            avl2 = liidaAVLpuud(avl1.v, avl2);
            avl2 = lisaKirje(avl2, avl1.väärtus);
            avl2 = tasakaalusta(avl2);
            avl2 = liidaAVLpuud(avl1.p, avl2);
        }
        return avl2;
    }

    /**
     * Abimeetod AVL puu tasakaalustamiseks
     * @param juur
     * @return tagastab puu peale tasakaalustamist
     */
    public static KOTipp tasakaalusta(KOTipp juur) {
        if (juur == null)
            return null;
        else {
            int vasak = 0;
            int parem = 0;
            if (juur.v != null) vasak += juur.v.x;
            if (juur.p != null) parem += juur.p.x;
            int tasakaal = vasak - parem;
            if (tasakaal > 1) {
                if (juur.v.p != null) {
                    juur.v = vasakpööre(juur.v);
                }
                return parempööre(juur);
            }
            if (tasakaal < -1) {
                if (juur.p.v != null) {
                    juur.p = parempööre(juur.p);
                }
                // Teeme vasakpöörde
                return vasakpööre(juur);
            }
            juur.v = tasakaalusta(juur.v);
            juur.p = tasakaalusta(juur.p);
            juur.x = Math.max(vasak, parem) + 1;
            return juur;
        }
    }

    /**
     * Abimeetod vasakpöörde tegemiseks
     * @param juur
     * @return tagastab juurtipu peale vasakpööret
     */
    public static KOTipp vasakpööre(KOTipp juur){
        KOTipp algseVasak = juur.v;
        KOTipp uusJuur = juur.p;
        KOTipp uueParem = juur.p.p;
        KOTipp uueVasak = juur.p.v;
        uusJuur.v = juur;
        uusJuur.p = uueParem;
        juur.v = algseVasak;
        juur.p = uueVasak;
        return uusJuur;
    }

    /**
     * Abimeetod parempöörde tegemiseks
     * @param juur
     * @return tagastab juurtipu pärast parempööret
     */
    public static KOTipp parempööre(KOTipp juur){
        KOTipp algseParem = juur.p;
        KOTipp uusJuur = juur.v;
        KOTipp uueParem = juur.v.p;
        KOTipp uueVasak = juur.v.v;
        uusJuur.p = juur;
        uusJuur.v = uueVasak;
        juur.v = uueParem;
        juur.p = algseParem;
        return uusJuur;
    }

    /**
     * Abimeetod kontrollimaks, kas puu on AVL puu
     * @param juur
     * @return tagastab tõeväärtuse
     */
    public static boolean onAVL(KOTipp juur) {
        if (juur == null)
            return true;
        int vasak = kõrgus(juur.v);
        int parem = kõrgus(juur.p);
        if (Math.abs(vasak - parem) > 1)
            return false;
        return onAVL(juur.v) && onAVL(juur.p);
    }

    /**
     * Abimeetod puu kõrguse leidmiseks
     * @param juur
     * @return tagastab kõrguse int
     */
    public static int kõrgus(KOTipp juur) {
        if (juur == null)
            return 0;
        return 1 + Math.max(kõrgus(juur.v), kõrgus(juur.p));
    }
}