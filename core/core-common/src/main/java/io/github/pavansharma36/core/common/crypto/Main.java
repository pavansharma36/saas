package io.github.pavansharma36.core.common.crypto;

import java.util.Base64;

public class Main {

  public static void main(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("2 args required");
    }

    switch (args[0]) {
      case "e":
        System.out.println(
            Base64.getEncoder().encodeToString(CryptUtil.encrypt(args[1]).getEncryptedValue()));
        break;
      case "d":
        System.out.println(CryptUtil.decryptEncoded(args[1]));
        break;
      default:
        throw new IllegalArgumentException("Invalid operation");
    }
  }

}
