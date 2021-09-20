package ru.projectx.clicker.utils;

public class Hash {
    /**
     * @param txt, text in plain format
     * @return hash in hashType
     */
    public static String getHash(String txt) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(txt.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Why is MD5 not present???");
    }
}
