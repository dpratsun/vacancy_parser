package ru.job4j.vacanciesparser.entity;

public class Vacancy {
    private long id;
    private String name;
    private String text;
    private String url;

    public Vacancy(long id, String name, String text, String url) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vacancy vacancy = (Vacancy) o;

        if (id != vacancy.id) {
            return false;
        }

        return name.equals(vacancy.name) && text.equals(vacancy.text) && url.equals(vacancy.url);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
