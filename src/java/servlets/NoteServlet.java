package servlets;

import businesslogic.NoteService;
import domainmodel.Note;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoteServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        NoteService ns = new NoteService();
        String action = request.getParameter("action");
        if (action != null && action.equals("view"))
        {
            String selectedNoteId = request.getParameter("selectedNoteId");
            try
            {
                Note note = ns.get(Integer.parseInt(selectedNoteId));
                request.setAttribute("selectedNote", note);
            } catch (Exception ex)
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        ArrayList<Note> notes = null;
        try
        {
            notes = (ArrayList<Note>) ns.getAll();
        } catch (Exception ex)
        {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String noteId = request.getParameter("noteId");
        String contents = request.getParameter("contents");
        String action = request.getParameter("action");

        NoteService ns = new NoteService();

        try
        {
            if (action.equals("delete"))
            {
                String selectedNoteId = request.getParameter("selectedNoteId");
                ns.delete(Long.parseLong(selectedNoteId));
            } else if (action.equals("edit"))
            {
                Note note = new Note(Integer.parseInt(noteId), contents);

                ns.update(note.getNoteId(), note.getContents());
            } else if (action.equals("add"))
            {
                ns.insert(contents);
            }
        } catch (Exception ex)
        {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }

        ArrayList<Note> notes = null;
        try
        {
            notes = (ArrayList<Note>) ns.getAll();
        } catch (Exception ex)
        {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
}
