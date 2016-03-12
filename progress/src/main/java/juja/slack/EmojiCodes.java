package juja.slack;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public enum EmojiCodes {
    MARKED("white_check_mark");

    private final String code;

    EmojiCodes(final String emoji) {
        this.code = emoji;
    }

    public String getCode() {
        return this.code;
    }
}
