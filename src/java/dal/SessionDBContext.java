/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;


public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> getSessionByGidAndLeid(int leid_input, int gid_input) {
        ArrayList<Session> sessions = new ArrayList<>();
        GroupDBContext gDB = new GroupDBContext();
        RoomDBContext rDB = new RoomDBContext();
        TimeSlotDBContext tiDB = new TimeSlotDBContext();
        LecturerDBContext leDB = new LecturerDBContext();

        ArrayList<Group> groups = gDB.list();
        ArrayList<Lecturer> lecturers = leDB.list();
        ArrayList<Room> rooms = rDB.list();
        ArrayList<TimeSlot> ts = tiDB.list();
        try {
            String sql_get_Sesion = "SELECT * FROM [Session]\n"
                    + "WHERE gid = ? AND leid = ?";
            PreparedStatement stm = connection.prepareStatement(sql_get_Sesion);
            stm.setInt(1, gid_input);
            stm.setInt(2, leid_input);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();

                s.setId(rs.getInt("seid"));
                int gid = rs.getInt("gid");
                s.setGroup(groups.stream().filter(t -> t.getId() == gid).findAny().get());
                int rid = rs.getInt("rid");
                s.setRoom(rooms.stream().filter(
                        r -> r.getId() == rid).findAny().get());
                s.setDate(rs.getDate("date"));
                int tid = rs.getInt("tid");
                // stream api
                s.setTimeslot(ts.stream().filter(t -> t.getId() == tid).findAny().get());
                int leid = rs.getInt("leid");
                s.setLecturer(lecturers.stream().filter(t -> t.getId() == leid).findAny().get());
                s.setIndex(rs.getInt("index"));
                s.setAttendated(rs.getBoolean("attend"));
                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;

    }

    public ArrayList<Session> filter(int leid_input, Date from, Date to) {

        ArrayList<Session> sessions = new ArrayList<>();
        GroupDBContext gDB = new GroupDBContext();
        RoomDBContext rDB = new RoomDBContext();
        TimeSlotDBContext tiDB = new TimeSlotDBContext();
        LecturerDBContext leDB = new LecturerDBContext();

        ArrayList<Group> groups = gDB.list();
        ArrayList<Lecturer> lecturers = leDB.list();
        ArrayList<Room> rooms = rDB.list();
        ArrayList<TimeSlot> ts = tiDB.list();
        try {
            String sql_get_Sesion = "SELECT \n"
                    + "ses.seid,ses.[date],ses.[index],ses.attend,\n"
                    + "l.leid,l.lename,\n"
                    + "g.gid,g.gname,\n"
                    + "sub.subid,sub.subname,\n"
                    + "r.rid,r.rname,\n"
                    + "t.tid,t.[description]\n"
                    + "FROM [Session] ses\n"
                    + "INNER JOIN Lecturer l ON l.leid = ses.leid\n"
                    + "INNER JOIN [Group] g ON g.gid = ses.gid\n"
                    + "INNER JOIN [Subject] sub ON sub.subid = g.subid\n"
                    + "INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "INNER JOIN TimeSlot t ON t.tid = ses.tid\n"
                    + "WHERE\n"
                    + "l.leid = ?\n"
                    + "AND ses.[date] >= ?\n"
                    + "AND ses.[date] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql_get_Sesion);
            stm.setInt(1, leid_input);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();

                s.setId(rs.getInt("seid"));
                int gid = rs.getInt("gid");
                s.setGroup(groups.stream().filter(t -> t.getId() == gid).findAny().get());
                int rid = rs.getInt("rid");
                s.setRoom(rooms.stream().filter(
                        r -> r.getId() == rid).findAny().get());
                s.setDate(rs.getDate("date"));
                int tid = rs.getInt("tid");
                // stream api
                s.setTimeslot(ts.stream().filter(t -> t.getId() == tid).findAny().get());
                int leid = rs.getInt("leid");
                s.setLecturer(lecturers.stream().filter(t -> t.getId() == leid).findAny().get());
                s.setIndex(rs.getInt("index"));
                s.setAttendated(rs.getBoolean("attend"));

                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    public ArrayList<Session> filterForStudent(int stuid_input, Date from, Date to) {

        ArrayList<Session> sessions = new ArrayList<>();
        GroupDBContext gDB = new GroupDBContext();
        RoomDBContext rDB = new RoomDBContext();
        TimeSlotDBContext tiDB = new TimeSlotDBContext();
        LecturerDBContext leDB = new LecturerDBContext();

        ArrayList<Group> groups = gDB.list();
        ArrayList<Lecturer> lecturers = leDB.list();
        ArrayList<Room> rooms = rDB.list();
        ArrayList<TimeSlot> ts = tiDB.list();
        try {
            String sql_get_Sesion = "SELECT se.seid,se.gid,se.rid,se.[date],se.tid,se.leid,se.attend,se.[index] FROM [Session] se\n"
                    + "INNER JOIN [Group] g ON se.gid = g.gid\n"
                    + "INNER JOIN Student_Group sg ON sg.gid = g.gid\n"
                    + "INNER JOIN Student s ON s.stuid = sg.stuid\n"
                    + "WHERE s.stuid = ? "
                    + "AND se.[date] >= ? "
                    + "AND se.[date] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql_get_Sesion);
            stm.setInt(1, stuid_input);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();

                session.setId(rs.getInt("seid"));
                session.setDate(rs.getDate("date"));
                session.setIndex(rs.getInt("index"));
                session.setAttendated(rs.getBoolean("attend"));

                int gid = rs.getInt("gid");
                int rid = rs.getInt("rid");
                int tid = rs.getInt("tid");
                int leid = rs.getInt("leid");
                
                session.setLecturer(lecturers.stream().filter(l->l.getId()==leid).findAny().get());
                session.setGroup(groups.stream().filter(g->g.getId()==gid).findAny().get());
                session.setRoom(rooms.stream().filter(r->r.getId()==rid).findAny().get());
                session.setTimeslot(ts.stream().filter(t->t.getId()==tid).findAny().get());

                sessions.add(session);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [Session] SET attend = 1 WHERE seid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());
            stm.executeUpdate();

            //remove old attandances
            sql = "DELETE Attendance WHERE seid = ?";
            PreparedStatement stm_delete = connection.prepareStatement(sql);
            stm_delete.setInt(1, model.getId());
            stm_delete.executeUpdate();

            //insert new attandances
            for (Attendance att : model.getAttendances()) {
                sql = "INSERT INTO [Attendance]\n"
                        + "           ([seid]\n"
                        + "           ,[stuid]\n"
                        + "           ,[present]\n"
                        + "           ,[description])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?)";
                PreparedStatement stm_insert = connection.prepareStatement(sql);
                stm_insert.setInt(1, model.getId());
                stm_insert.setInt(2, att.getStudent().getId());
                stm_insert.setBoolean(3, att.isPresent());
                stm_insert.setString(4, att.getDescription());
                stm_insert.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        try {
            String sql = "SELECT ses.seid,ses.gid,ses.date,ses.tid,ses.leid,ses.attend,ses.[index],\n"
                    + "g.gid,g.gname,\n"
                    + "r.rid,r.rname,\n"
                    + "t.tid,t.[description] tdes,t.tname,\n"
                    + "l.leid,l.lename,\n"
                    + "s.subid,s.subname,\n"
                    + "st.stuid,st.stuname,\n"
                    + "ISNULL(a.present,0) present, ISNULL(a.[description],'')[description]\n"
                    + "FROM Session ses \n"
                    + "INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "INNER JOIN TimeSlot t ON t.tid = ses.tid\n"
                    + "INNER JOIN Lecturer l ON l.leid = ses.leid\n"
                    + "INNER JOIN [Group] g ON g.gid = ses.gid\n"
                    + "INNER JOIN [Subject] s ON s.subid = g.subid\n"
                    + "INNER JOIN [Student_Group] sg ON sg.gid = g.gid\n"
                    + "INNER JOIN [Student] st ON st.stuid = sg.stuid\n"
                    + "LEFT JOIN Attendance a ON a.stuid = st.stuid AND ses.seid = a.seid\n"
                    + "WHERE ses.seid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Session ses = null;
            while (rs.next()) {
                if (ses == null) {
                    ses = new Session();
                    Room r = new Room();
                    r.setId(rs.getInt("rid"));
                    r.setName(rs.getString("rname"));
                    ses.setRoom(r);

                    TimeSlot t = new TimeSlot();
                    t.setId(rs.getInt("tid"));
                    t.setDescription(rs.getString("tdes"));
                    t.setName(rs.getString("tname"));
                    ses.setTimeslot(t);

                    Lecturer l = new Lecturer();
                    l.setId(rs.getInt("leid"));
                    l.setName(rs.getString("lename"));
                    ses.setLecturer(l);

                    Group g = new Group();
                    g.setId(rs.getInt("gid"));
                    g.setName(rs.getString("gname"));
                    ses.setGroup(g);

                    Subject sub = new Subject();
                    sub.setId(rs.getInt("subid"));
                    sub.setName(rs.getString("subname"));
                    g.setSubject(sub);

                    ses.setDate(rs.getDate("date"));
                    ses.setIndex(rs.getInt("index"));
                    ses.setAttendated(rs.getBoolean("attend"));
                }
                //read student
                Student s = new Student();
                s.setId(rs.getInt("stuid"));
                s.setName(rs.getString("stuname"));
                //read attandance
                Attendance a = new Attendance();
                a.setStudent(s);
                a.setSession(ses);
                a.setPresent(rs.getBoolean("present"));
                a.setDescription(rs.getString("description"));
                ses.getAttendances().add(a);
            }
            return ses;
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Session> list() {
        ArrayList<Session> sessions = new ArrayList<>();

        LecturerDBContext leDB = new LecturerDBContext();
        GroupDBContext gDB = new GroupDBContext();
        TimeSlotDBContext tiDB = new TimeSlotDBContext();
        RoomDBContext rDB = new RoomDBContext();

        ArrayList<Lecturer> ls = leDB.list();
        ArrayList<Room> ros = rDB.list();
        ArrayList<TimeSlot> ts = tiDB.list();
        ArrayList<Group> gs = gDB.list();

        try {
            String sql_get_Sesion = "SELECT * FROM [Session]";
            PreparedStatement stm = connection.prepareStatement(sql_get_Sesion);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Session s = new Session();
                s.setId(rs.getInt("seid"));
                s.setIndex(rs.getInt("index"));
                s.setAttendated(rs.getBoolean("attend"));
                s.setDate(rs.getDate("date"));

                int leid = rs.getInt("leid");
                s.setLecturer(ls.stream().filter(l -> l.getId() == leid).findAny().get());
                int rid = rs.getInt("rid");
                s.setRoom(ros.stream().filter(ro -> ro.getId() == rid).findAny().get());
                int tid = rs.getInt("tid");
                s.setTimeslot(ts.stream().filter(t -> t.getId() == tid).findAny().get());
                int gid = rs.getInt("gid");
                s.setGroup(gs.stream().filter(g -> g.getId() == gid).findAny().get());
                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;

    }

    public ArrayList<Session> listSessionByGid(int gid_input) {
        ArrayList<Session> sessions = new ArrayList<>();

        LecturerDBContext leDB = new LecturerDBContext();
        GroupDBContext gDB = new GroupDBContext();
        TimeSlotDBContext tiDB = new TimeSlotDBContext();
        RoomDBContext rDB = new RoomDBContext();

        ArrayList<Lecturer> ls = leDB.list();
        ArrayList<Room> ros = rDB.list();
        ArrayList<TimeSlot> ts = tiDB.list();
        ArrayList<Group> gs = gDB.list();

        try {
            String sql_get_Sesion = "SELECT * FROM [Session] WHERE gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql_get_Sesion);
            stm.setInt(1, gid_input);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Session s = new Session();
                s.setId(rs.getInt("seid"));
                s.setIndex(rs.getInt("index"));
                s.setAttendated(rs.getBoolean("attend"));
                s.setDate(rs.getDate("date"));

                int leid = rs.getInt("leid");
                s.setLecturer(ls.stream().filter(l -> l.getId() == leid).findAny().get());
                int rid = rs.getInt("rid");
                s.setRoom(ros.stream().filter(ro -> ro.getId() == rid).findAny().get());
                int tid = rs.getInt("tid");
                s.setTimeslot(ts.stream().filter(t -> t.getId() == tid).findAny().get());
                int gid = rs.getInt("gid");
                s.setGroup(gs.stream().filter(g -> g.getId() == gid).findAny().get());
                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;

    }
    public static void main(String[] args) {
        ArrayList<Session> sessions = new ArrayList<>();
        GroupDBContext gDB = new GroupDBContext();
        RoomDBContext rDB = new RoomDBContext();
        TimeSlotDBContext tiDB = new TimeSlotDBContext();
        LecturerDBContext leDB = new LecturerDBContext();
        SessionDBContext s = new SessionDBContext();
        sessions = s.getSessionByGidAndLeid(1, 1);
        System.out.println(sessions.get(0).getDate());
    }
}
