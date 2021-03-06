package gamarket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Player {
    private String name;
    private String email;
    private String password;
    private int badges;
    private double money;
    private int totalPokemon;
    private String joinDate;
    private String time1;
    private String time2;
    private String totalTime;
    private Team pokeTeam;
    private MoveCollection moveCollection;
    private PokemonCollection pokeDex; //TODO
    private Bag bag;


    /**
     * Player constructor initializes either a new player or returning player
     * @param newUser lets the constructor know whether it's a new user or not
     * @param un gives the constructor the player's username
     * @param pw gives the constructor the player's password
     **/
    public Player(Boolean newUser, String un, String pw, boolean... test) {
        System.out.println("does this ever run");
        if (newUser) {
            Date originalDate = new Date();
            //format
            this.joinDate = originalDate.toString();
            this.name = un;
            this.password = pw;
            this.badges = 0;
            this.money = 0;
            this.totalPokemon = 0;
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            this.joinDate = formatter.format(originalDate);
            formatter = new SimpleDateFormat("HH:mm:ss");
            this.time1 = formatter.format(originalDate);
            this.pokeTeam.loadTeam("default");
            this.bag = Bag.getInstance();
        } else if(test.length > 0 && test[0] == true){
            this.name = un;
            this.password = pw;
            loadData(this.name, true);
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Date currentTime = new Date();
            this.time1 = timeFormat.format(currentTime);
            this.bag = Bag.getInstance();
            this.bag.loadData(this.name,true);
        } else {
            this.name = un;
            this.password = pw;
            loadData(this.name);
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Date currentTime = new Date();
            this.time1 = timeFormat.format(currentTime);
            this.bag = Bag.getInstance();
            this.bag.loadData(this.name);
            this.time2 = time1;
        }
    }
    /**
     * needed for firebase getData
     */
    public  Player () {

    }

    public void setUpPlayer(){
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        this.time1 = timeFormat.format(currentTime);
        this.bag = Bag.getInstance();
        this.bag.loadData(this.name);
    }

    public void setName(String username) {
        this.name = username;
    }
    public void setEmail(String e) {
        this.email = e;
    }
    public void setPassword(String pw) {
        this.password = pw;
    }
    public void setBadges(int amt){
        this.badges = amt;
    }
    public void setMoney(long amt){ this.money = amt; }
    public void setTotalPokemon(int amt){ this.totalPokemon = amt; }
    public String getName(){ return name; }
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }
    public int getBadges(){ return badges; }
    public double getMoney(){ return money; }
    public int getTotalPokemon(){ return totalPokemon; }
    // public PokemonCollection getPokeDex(){ return pokeDex; }


    /**
     * loadData loads the returning player's saved data
     * @param fileName takes in the file name where the player's profile is saved
     *                 Proper player profile format:
     *                 playerName,email,password
     *                 amountOfBadges,money,totalPokemon
     *                 joinDate,totalTime,
     **/
    public void loadData(String fileName, boolean... test) {
        String filePath;
        if(test.length > 0 && test[0]==true){
            filePath = "./databaseFiles/userProfiles/" + fileName + "_profile.txt";
        }else{
            filePath = "./pokemon/databaseFiles/userProfiles/" + fileName + "_profile.txt";
        }
        File inFile = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(inFile);
            String data = scanner.nextLine();
            String temp[] = data.split(",");
            this.name = temp[0];
            this.email = temp[1];
            this.password = temp[2];
            this.badges = Integer.parseInt(temp[3]);
            this.money = Double.parseDouble(temp[4]);
            this.totalPokemon = Integer.parseInt(temp[5]);
            this.joinDate = temp[6];
            this.totalTime = temp[7];

            if(test.length > 0 && test[0]==true){
                this.pokeTeam.loadTeam(fileName, true);
            }else {
                this.pokeTeam.loadTeam(fileName);
            }
            //  These lines of code are commented out due to errors when calling respective class methods
            // due to source files are not set up correctly yet
            // this.pokeDex.loadData(name);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void saveToDB () {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("player");
        DatabaseReference playerRef = ref.child(this.name);
        Map<String, Object> players = new HashMap<>();
        players.put("name", this.name);
        players.put("badges",this.badges);
        players.put("email", this.email);
        players.put("money", this.money);
        players.put("totalPokemon", this.totalPokemon);
        players.put("password", this.password);
        playerRef.setValueAsync(players);
    }

    public DatabaseReference loadFromDb (String... playerName) {
        String playerString;
        if(playerName.length > 0) {
            playerString = playerName[0];
        }
        else {
            playerString = this.name;
        } 
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference playersRef = database.getReference("player/");
        DatabaseReference ref = playersRef.child(playerString);
        return ref;
    }
    /**
     * saveData saves the user's data in the proper format
     * Proper player profile format:
     * playerName,email,password,amountOfBadges,money,totalPokemon,joinDate,totalTime
     **/
    public void saveData(boolean... test) {
        File file;
        if(test.length>0 && test[0]==true){
            file = new File("./databaseFiles/userProfiles/" + this.name + "_profile.txt");
            this.bag.saveData(this.name,true);
        }else{
            file = new File("./pokemon/databaseFiles/userProfiles/" + this.name + "_profile.txt");
          //  this.bag.saveData(this.name);
        }

        try {
           FileWriter writer = new FileWriter(file);
           SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
           Date currentTime = new Date();
           totalTime = calculateTime(timeFormat.format(currentTime));
           writer.write(this.name + "," + this.email + "," + this.password + ","
                   + this.badges + "," + this.money + "," + this.totalPokemon + ","
                   + this.joinDate + "," + this.totalTime);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * calculateTime calculates the difference between the time passed in and the last saved time
     * @param time takes in a string that should hold the time of method call
     * @return returns a string of the calculated time
     **/
    private String calculateTime(String time) {
        time2 = time;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        Date date1;
        Date date2;
        try {
             date1 = timeFormat.parse(this.time1);
             date2 = timeFormat.parse(time2);
             long diff = date2.getTime() - date1.getTime();
             time1 = timeFormat.format(new Date(diff));
             time1 = time1.replaceFirst("16", "00");
        } catch (ParseException e) {
             e.printStackTrace();
        }

        return time1;
    }

    public String toString(){
        return "Username: " + this.name +
                "\nEmail: " + this.email +
                "\nPassword: " + this.password +
                "\nBages: " + this.badges +
                "\nMoney: " + this.money +
                "\nTotal Pokemon: " + this.totalPokemon +
                "\nJoin date: " + this.joinDate +
                "\nPlayed Time: " + this.totalTime +
                "\nPokemon Team: " + "TODO" +
                "\nPokedex: " + "TODO";
    }

    public String getJoinDate() {
        return this.joinDate;
    }

    public String getTimePlayed() {
        return this.totalTime;
    }

    public Bag getBag() {
        return this.bag;
    }

    public Team getPokeTeam() { return this.pokeTeam; }
}