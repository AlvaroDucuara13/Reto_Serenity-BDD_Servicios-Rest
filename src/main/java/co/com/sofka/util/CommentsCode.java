package co.com.sofka.util;

public enum CommentsCode {
    PostId("[postId]"),
    IdUser("[id]"),
    CompleteName("[name]"),
    Email("[email]"),
    Job ("[job]");

    private final String value;

    CommentsCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
