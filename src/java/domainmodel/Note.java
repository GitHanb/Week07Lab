package domainmodel;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable
{

    private int noteId;
    private java.util.Date dateCreated;
    private String contents;

    public Note()
    {

    }

    public Note(String contents)
    {
        this.dateCreated = new Date();
        this.contents = contents;
    }

    public Note(int noteId, String contents)
    {
        this.noteId = noteId;
        this.dateCreated = new Date();
        this.contents = contents;
    }

    public Note(int noteId, java.util.Date dateCreated, String contents)
    {
        this.noteId = noteId;
        this.dateCreated = dateCreated;
        this.contents = contents;
    }

    public int getNoteId()
    {
        return noteId;
    }

    public void setNoteId(int noteId)
    {
        this.noteId = noteId;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public String getContents()
    {
        return contents;
    }

    public void setContents(String contents)
    {
        this.contents = contents;
    }

}
