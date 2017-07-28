package com.daiqile.xianjindai.utils.utils;

/**
 * Created by zkw on 2017/7/28.
 */

public class EnvConstants {
    private EnvConstants() {
    }

    /**
     * TODO 商户号，商户MD5 key 配置。本测试Demo里的“PARTNER”；强烈建议将私钥配置到服务器上，以免泄露。“MD5_KEY”字段均为测试字段。正式接入需要填写商户自己的字段
     */
//    public static final String PARTNER_PREAUTH = "201504071000272504"; // 短信

//    public static final String MD5_KEY_PREAUTH = "201504071000272504_test_20150417";

//    public static final String PARTNER = "201707112690";//商户编号是商户在连连钱包支付平台上开设的商户号码
    public static final String PARTNER = "201707191001893509";//商户编号是商户在连连钱包支付平台上开设的商户号码

    public static final String MD5_KEY = "201408071000001543test_20140812";

    // 商户（RSA）私钥 TODO 强烈建议将私钥配置到服务器上，否则有安全隐患
//    public static final String RSA_PRIVATE =
//            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOilN4tR7HpNYvSBra/DzebemoAiGtGeaxa+qebx/O2YAdUFPI+xTKTX2ETyqSzGfbxXpmSax7tXOdoa3uyaFnhKRGRvLdq1kTSTu7q5s6gTryxVH2m62Py8Pw0sKcuuV0CxtxkrxUzGQN+QSxf+TyNAv5rYi/ayvsDgWdB3cRqbAgMBAAECgYEAj02d/jqTcO6UQspSY484GLsL7luTq4Vqr5L4cyKiSvQ0RLQ6DsUG0g+Gz0muPb9ymf5fp17UIyjioN+ma5WquncHGm6ElIuRv2jYbGOnl9q2cMyNsAZCiSWfR++op+6UZbzpoNDiYzeKbNUz6L1fJjzCt52w/RbkDncJd2mVDRkCQQD/Uz3QnrWfCeWmBbsAZVoM57n01k7hyLWmDMYoKh8vnzKjrWScDkaQ6qGTbPVL3x0EBoxgb/smnT6/A5XyB9bvAkEA6UKhP1KLi/ImaLFUgLvEvmbUrpzY2I1+jgdsoj9Bm4a8K+KROsnNAIvRsKNgJPWd64uuQntUFPKkcyfBV1MXFQJBAJGs3Mf6xYVIEE75VgiTyx0x2VdoLvmDmqBzCVxBLCnvmuToOU8QlhJ4zFdhA1OWqOdzFQSw34rYjMRPN24wKuECQEqpYhVzpWkA9BxUjli6QUo0feT6HUqLV7O8WqBAIQ7X/IkLdzLa/vwqxM6GLLMHzylixz9OXGZsGAkn83GxDdUCQA9+pQOitY0WranUHeZFKWAHZszSjtbe6wDAdiKdXCfig0/rOdxAODCbQrQs7PYy1ed8DuVQlHPwRGtokVGHATU=";
  public static final String RSA_PRIVATE ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOpBzPjBrZHrWYX6g0iWe5yQdVyCd7n/DTH2b5ZL1IOWoHJrDuA83qQDBqXQFSnu2DmomLLc9JsInb4/nuD3vwe5a3FiXrLrzLsK+NfnSGjqPiSRhA7weTzjnQa9vdUonV7QocrMf1lv0ktl75zH1jYoJhqS/1JR0oE+udFQHb4fAgMBAAECgYBrIfSvv0O1/EBQ+aSYydl2XMqPqEtBENfkBaQ6xCtIktaF/VgsTSo1L/0WT/ed2dGhbO2Iv2xOir45h7cNg8Ak1ASb3viNn/us9bff0syQaSDQrj+eFMBu6mNxm7h2/aThwc/VvawRfH3O0XXgbla6x5FvFRCy5GU3DtDxCAGEoQJBAP9AB9ar2nByHoEnko8G6AyJZTdWy5SCX0cYAJb1o7E+U0Ch0/FfDEHFmu2Q8z0VpD/8c/M5+B9v13yUnS3Nt3MCQQDq8ftDknxvqIjHaQUF4VXE7QCMKHFpKi9GvqkpMKW2aD2gLjpRxDvANuOn6/zVwIa3GYCTraRcM+VGICYEBTulAkEA3chVB0HrWyjNOIuZ3Iyzs7DuMVdJLJDbGHrSiFiHEsqEq/PwpBliHQQFycXYk4rqXG6OkRgdb7NOGQFIUTQd4QJAM8UYcb5QjyYa5Z3djnsltBvpN/kdbGfwPO18RuhCS5xSn/4vuZ6879DofzL/5nXi+Wu0KlZj1jFVPwuMdOxWRQJBAPpABexXiwu9IMaCYSsQs+o95oqv5wqO57FD2qWddmEXYtlxwtZ7I/q5JedqturpYk/D9P4ZqqK6OVZuZNvyIF0=";
//     银通支付（RSA）公钥
    public static final String RSA_YT_PUBLIC =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSS/DiwdCf/aZsxxcacDnooGph3d2JOj5GXWi+q3gznZauZjkNP8SKl3J2liP0O6rU/Y/29+IUe+GTMhMOFJuZm1htAtKiu5ekW0GlBMWxf4FPkYlQkPE0FtaoMP3gYfh+OwI+fIRrpW3ySn3mScnc6Z700nU/VYrRkfcSCbSnRwIDAQAB";

}
