package main;

import actor.Actors;
import checker.Checker;
import checker.Checkstyle;
import common.Constants;
import entities.userstype.Premiumusers;
import entities.userstype.Standardusers;
import entities.userstype.Users;
import entities.videotype.Movies;
import entities.videotype.Ratingmovies;
import entities.videotype.shows.Ratingshows;
import entities.videotype.shows.Shows;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import org.json.simple.JSONArray;
import query.actors.Queryactors;
import query.users.Queryuser;
import query.videos.Querymovie;
import query.videos.Queryshows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your
 * implentation.
 */
@SuppressWarnings("checkstyle:JavadocStyle")
public final class Main {
    /**
     * for coding style.
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker.
     *
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH,
                Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    @SuppressWarnings("unchecked")
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();
        ArrayList<Standardusers> standardUsers = new ArrayList<>();
        ArrayList<Premiumusers> premiumUsers = new ArrayList<>();
        ArrayList<Users> allUsers = new ArrayList<>();

        for (int i = 0; i < input.getUsers().size(); i++) {

            if (input.getUsers().get(i).getSubscriptionType().equals("BASIC")) {

                Standardusers standardUser =
                        new Standardusers(input.getUsers().get(i).getUsername(),
                                input.getUsers().get(i).getSubscriptionType(),
                                input.getUsers().get(i).getHistory(),
                                input.getUsers().get(i).getFavoriteMovies());

                standardUsers.add(standardUsers.size(), standardUser);
            } else {

                Premiumusers premiumuser =
                        new Premiumusers(input.getUsers().get(i).getUsername(),
                                input.getUsers().get(i).getSubscriptionType(),
                                input.getUsers().get(i).getHistory(),
                                input.getUsers().get(i).getFavoriteMovies());
                premiumUsers.add(premiumUsers.size(), premiumuser);

            }

            Users user = new Users(input.getUsers().get(i).getUsername(),
                    input.getUsers().get(i).getSubscriptionType(),
                    input.getUsers().get(i).getHistory(),
                    input.getUsers().get(i).getFavoriteMovies());
            allUsers.add(allUsers.size(), user);

        }


        ArrayList<Movies> movies = new ArrayList<>();
        for (int i = 0; i < input.getMovies().size(); i++) {
            Movies movie = new Movies(input.getMovies().get(i).getTitle(),
                    input.getMovies().get(i).getYear(),
                    input.getMovies().get(i).getCast(),
                    input.getMovies().get(i).getGenres(),
                    input.getMovies().get(i).getDuration());
            movies.add(i, movie);
        }

        ArrayList<Shows> shows = new ArrayList<>();
        var helpShow = input.getSerials();
        for (int i = 0; i < helpShow.size(); i++) {
            Shows show = new Shows(helpShow.get(i).getTitle(),
                    helpShow.get(i).getYear(),
                    helpShow.get(i).getCast(),
                    helpShow.get(i).getGenres(),
                    helpShow.get(i).getNumberSeason(),
                    helpShow.get(i).getSeasons());
            shows.add(i, show);
        }


        Queryuser queryuser = new Queryuser(allUsers);
        Querymovie querymovie = new Querymovie();
        Queryshows queryshows = new Queryshows();


        for (int i = 0; i < input.getCommands().size(); i++) {

            int userindex =
                    search(allUsers, input.getCommands().get(i).getUsername());
            int premiumuserindex =
                    premiumSearch(premiumUsers,
                            input.getCommands().get(i).getUsername());

            if (input.getCommands().get(i).getActionType().equals("command")) {

                if (input.getCommands().get(i).getType().equals("favorite")) {
                    arrayResult.add(fileWriter
                            .writeFile(input.getCommands().get(i).getActionId(),
                                    "", allUsers.get(userindex)
                                            .favoriteCommand(
                                                    input.getCommands().get(i)
                                                            .getTitle())));
                }

                if (input.getCommands().get(i).getType().equals("view")) {
                    arrayResult.add(fileWriter
                            .writeFile(input.getCommands().get(i).getActionId(),
                                    "", allUsers.get(userindex)
                                            .viewCommand(
                                                    input.getCommands().get(i)
                                                            .getTitle())));
                }
                if (input.getCommands().get(i).getType().equals("rating")) {

                    if (input.getCommands().get(i).getSeasonNumber() == 0) {
                        Ratingmovies ratingmovies =
                                new Ratingmovies(
                                        input.getCommands().get(i).getTitle(),
                                        input.getCommands().get(i).getGrade());
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "",
                                        allUsers.get(userindex)
                                                .ratingCommandMovie(
                                                        ratingmovies, movies,
                                                        input.getCommands()
                                                                .get(i)
                                                                .getGrade())
                                                .getFirst()));
                        movies = allUsers.get(userindex)
                                .ratingCommandMovie(ratingmovies, movies,
                                        input.getCommands().get(i).getGrade())
                                .getSecond();

                    } else {
                        Ratingshows ratingshows =
                                new Ratingshows(
                                        input.getCommands().get(i).getTitle(),
                                        input.getCommands().get(i)
                                                .getSeasonNumber(),
                                        input.getCommands().get(i).getGrade());
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "", allUsers.get(userindex)
                                                .ratingCommandShows(
                                                        ratingshows, shows,
                                                        input.getCommands()
                                                                .get(i)
                                                                .getGrade())
                                                .getFirst()));
                        shows = allUsers.get(userindex).ratingCommandShows(
                                ratingshows, shows,
                                input.getCommands().get(i).getGrade())
                                .getSecond();

                    }
                }
            }

            for (Shows show : shows) {
                show.setRating(show.ratingCalcualtor());
            }
            for (Movies movie : movies) {
                movie.setRating(movie.movieRatingCalculator());
            }




            ArrayList<Actors> actors = new ArrayList<>();
            var helpActor = input.getActors();
            for (int k = 0; k < helpActor.size(); k++) {
                Actors actor = new Actors(helpActor.get(k).getName(),
                        helpActor.get(k).getCareerDescription(),
                        helpActor.get(k).getFilmography(),
                        helpActor.get(k).getAwards(), movies,
                        shows);
                actors.add(k, actor);
            }

            Queryactors queryactors = new Queryactors();

            if (input.getCommands().get(i).getActionType().equals("query")) {

                if (input.getCommands().get(i).getObjectType()
                        .equals("actors")) {
                    if (input.getCommands().get(i).getCriteria()
                            .equals("average")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "", queryactors.average(actors,
                                                input.getCommands().get(i)
                                                        .getNumber(),
                                                input.getCommands().get(i)
                                                        .getSortType())));
                    }

                    if (input.getCommands().get(i).getCriteria()
                            .equals("awards")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "", queryactors.awards(actors,
                                                input.getCommands().get(i)
                                                        .getSortType(),
                                                input.getCommands().get(i)
                                                        .getFilters()
                                                        .get(input.getCommands()
                                                                .get(i)
                                                                .getFilters()
                                                                .size() - 1))));
                    }
                    if (input.getCommands().get(i).getCriteria()
                            .equals("filter_description")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "",
                                        queryactors.filterDescription(actors,
                                                input.getCommands().get(i)
                                                        .getSortType(),
                                                input.getCommands().get(i).
                                                        getFilters().get(2))));
                    }
                }
                if (input.getCommands().get(i).getObjectType()
                        .equals("movies")) {

                    if (input.getCommands().get(i).getCriteria()
                            .equals("ratings")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "",
                                        querymovie.movieRating(allUsers, movies,
                                                input.getCommands().get(i)
                                                        .getFilters(),
                                                input.getCommands().get(i)
                                                        .getNumber(),
                                                input.getCommands().get(i)
                                                        .getSortType())));
                    }
                    if (input.getCommands().get(i).getCriteria()
                            .equals("favorite")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "",
                                        querymovie.favorite(allUsers, movies,
                                                input.getCommands().get(i)
                                                        .getFilters(),
                                                input.getCommands().get(i)
                                                        .getNumber(),
                                                input.getCommands().get(i)
                                                        .getSortType())));
                    }
                    if (input.getCommands().get(i).getCriteria()
                            .equals("longest")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "", querymovie.longest(movies,
                                                input.getCommands().get(i)
                                                        .getFilters(),
                                                input.getCommands().get(i)
                                                        .getNumber(),
                                                input.getCommands().get(i)
                                                        .getSortType())));
                    }
                    if (input.getCommands().get(i).getCriteria()
                            .equals("most_viewed")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "",
                                        querymovie.mostViewd(allUsers, movies,
                                                input.getCommands().get(i)
                                                        .getFilters(),
                                                input.getCommands().get(i)
                                                        .getNumber(),
                                                input.getCommands().get(i)
                                                        .getSortType())));
                    }

                }
                if (input.getCommands().get(i).getObjectType()
                        .equals("shows")) {
                    if (input.getCommands().get(i).getCriteria()
                            .equals("ratings")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "", queryshows.rating(shows,
                                                input.getCommands().get(i)
                                                        .getFilters(),
                                                input.getCommands().get(i)
                                                        .getNumber(),
                                                input.getCommands().get(i)
                                                        .getSortType())));
                    }
                    if (input.getCommands().get(i).getCriteria()
                            .equals("favorite")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "", queryshows.favorite(allUsers, shows,
                                                input.getCommands().get(i)
                                                        .getFilters(),
                                                input.getCommands().get(i)
                                                        .getNumber(),
                                                input.getCommands().get(i)
                                                        .getSortType())));
                    }
                    if (input.getCommands().get(i).getCriteria()
                            .equals("longest")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "", queryshows.longest(shows,
                                                input.getCommands().get(i)
                                                        .getFilters(),
                                                input.getCommands().get(i)
                                                        .getNumber(),
                                                input.getCommands().get(i)
                                                        .getSortType())));
                    }
                    if (input.getCommands().get(i).getCriteria()
                            .equals("most_viewed")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "",
                                        queryshows.mostViewd(allUsers, shows,
                                                input.getCommands().get(i)
                                                        .getFilters(),
                                                input.getCommands().get(i)
                                                        .getNumber(),
                                                input.getCommands().get(i)
                                                        .getSortType())));
                    }

                }

                if (input.getCommands().get(i).getObjectType()
                        .equals("users")) {
                    arrayResult.add(fileWriter
                            .writeFile(input.getCommands().get(i).getActionId(),
                                    "", queryuser.numberOfRatings(
                                            queryuser.getUserlist(),
                                            input.getCommands().get(i)
                                                    .getNumber(),
                                            input.getCommands().get(i)
                                                    .getSortType())));
                }

            }

            if (input.getCommands().get(i).getActionType()
                    .equals("recommendation")) {
                if (input.getCommands().get(i).getType().equals("standard")) {
                    arrayResult.add(fileWriter
                            .writeFile(input.getCommands().get(i).getActionId(),
                                    "", allUsers.get(userindex)
                                            .standard(movies, shows)));
                }
                if (input.getCommands().get(i).getType()
                        .equals("best_unseen")) {
                    arrayResult.add(fileWriter
                            .writeFile(input.getCommands().get(i).getActionId(),
                                    "", allUsers.get(userindex)
                                            .bestUnseen(movies, shows)));
                }
                if (input.getCommands().get(i).getType().equals("favorite")) {
                    if (allUsers.get(userindex).getSubscriptiontype()
                            .equals("BASIC")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "",
                                        "FavoriteRecommendation cannot be "
                                                + "applied!"));
                    } else {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "", premiumUsers.get(premiumuserindex)
                                                .favoriterecommandation(
                                                        allUsers, movies,
                                                        shows)));
                    }
                }
                if (input.getCommands().get(i).getType().equals("search")) {
                    if (allUsers.get(userindex).getSubscriptiontype()
                            .equals("BASIC")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "",
                                        "SearchRecommendation cannot be "
                                                + "applied!"));
                    } else {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "", premiumUsers.get(premiumuserindex)
                                                .search(movies, shows,
                                                        input.getCommands()
                                                                .get(i)
                                                                .getGenre())));
                    }
                }
                if (input.getCommands().get(i).getType().equals("popular")) {
                    if (allUsers.get(userindex).getSubscriptiontype()
                            .equals("BASIC")) {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "",
                                        "PopularRecommendation cannot be "
                                                + "applied!"));
                    } else {
                        arrayResult
                                .add(fileWriter.writeFile(
                                        input.getCommands().get(i)
                                                .getActionId(),
                                        "", premiumUsers.get(premiumuserindex)
                                                .popular(premiumUsers, movies,
                                                        shows)));
                    }
                }
            }


        }


        //TODO add here the entry point to your implementation

        fileWriter.closeJSON(arrayResult);
    }

    private static int search(final ArrayList<Users> allusers,
                              final String name) {
        int index = 0;

        for (int i = 0; i < allusers.size(); i++) {
            if (allusers.get(i).getUsername().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static int premiumSearch(final ArrayList<Premiumusers> premiumusers,
                                     final String name) {
        int index = 0;

        for (int i = 0; i < premiumusers.size(); i++) {
            if (premiumusers.get(i).getUsername().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

}
