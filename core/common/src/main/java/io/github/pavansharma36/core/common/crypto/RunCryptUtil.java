package io.github.pavansharma36.core.common.crypto;

import io.github.pavansharma36.core.common.config.provider.ConfigProviders;

public class RunCryptUtil {

  public static void main(String[] args) {
    System.out.println(ConfigProviders.class);
    if (args.length != 2) {
      throw new IllegalArgumentException("2 args required");
    }

    switch (args[0]) {
      case "p":
        System.out.println(CryptUtil.encrypt(args[1]).encoded());
        break;
      case "e":
        System.out.println(CryptUtil.encrypt(args[1]).value());
        break;
      case "d":
        System.out.println(CryptUtil.decryptEncoded(args[1]));
        break;
      default:
        throw new IllegalArgumentException("Invalid operation");
    }
  }

}
