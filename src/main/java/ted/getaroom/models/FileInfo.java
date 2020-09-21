package ted.getaroom.models;

public class FileInfo {
    private String name;
    private String url;
    private String message;

    public FileInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public FileInfo(String name, String url, String message) {
        this.name = name;
        this.url = url;
        this.message = message;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
