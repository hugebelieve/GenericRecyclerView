package com.hugebelieve.genericrecyclerview;

import java.util.List;

public class FillTempMovieData {

    public static void fillData(List<ClassModel> data){
        data.add(createClassModel("The Meg",
                "After escaping an attack by what he claims was a 70-foot shark, Jonas Taylor must confront his fears to save those trapped in a sunken submersible.",
                "https://m.media-amazon.com/images/M/MV5BMjg0MzA4MDE0N15BMl5BanBnXkFtZTgwMzk3MzAwNjM@._V1_UX182_CR0,0,182,268_AL__QL100.jpg",
                "6.2"));
        data.add(createClassModel("The Equalizer 2",
                "Robert McCall serves an unflinching justice for the exploited and oppressed, but how far will he go when that is someone he loves?",
                "https://m.media-amazon.com/images/M/MV5BMTU2OTYzODQyMF5BMl5BanBnXkFtZTgwNjU3Njk5NTM@._V1_UX182_CR0,0,182,268_AL__QL100.jpg",
                "7.1"));
        data.add(createClassModel("Slender Man",
                "In a small town in Massachusetts, a group of friends, fascinated by the internet lore of the Slender Man, attempt to prove that he doesn't actually exist - until one of them mysteriously goes missing.",
                "https://m.media-amazon.com/images/M/MV5BMjE0MzcwMDAyNl5BMl5BanBnXkFtZTgwMzc4ODg0NDM@._V1_UX182_CR0,0,182,268_AL__QL100.jpg",
                "3.1"));
        data.add(createClassModel("Christopher Robin",
                "A working-class family man, Christopher Robin, encounters his childhood friend Winnie-the-Pooh, who helps him to rediscover the joys of life.",
                "https://m.media-amazon.com/images/M/MV5BMjAzOTM2OTAyNF5BMl5BanBnXkFtZTgwNTg5ODg1NTM@._V1_UX182_CR0,0,182,268_AL__QL100.jpg",
                "7.9"));
        data.add(createClassModel("BlacKkKlansman",
                "Ron Stallworth, an African-American police officer from Colorado, successfully managed to infiltrate the local Ku Klux Klan and became the head of the local chapter.",
                "https://m.media-amazon.com/images/M/MV5BMjUyOTE1NjI0OF5BMl5BanBnXkFtZTgwMTM4ODQ5NTM@._V1_UX182_CR0,0,182,268_AL__QL100.jpg",
                "7.6"));
        data.add(createClassModel("Ant-Man and the Wasp",
                "As Scott Lang balances being both a Super Hero and a father, Hope van Dyne and Dr. Hank Pym present an urgent new mission that finds the Ant-Man fighting alongside The Wasp to uncover secrets from their past.",
                "https://m.media-amazon.com/images/M/MV5BYjcyYTk0N2YtMzc4ZC00Y2E0LWFkNDgtNjE1MzZmMGE1YjY1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL__QL100.jpg",
                "7.4"));
        data.add(createClassModel("Skyscraper",
                "A security expert must infiltrate a burning skyscraper, 225 stories above ground, when his family are trapped inside by criminals.",
                "https://m.media-amazon.com/images/M/MV5BOGM3MzQwYzItNDA1Ny00MzIyLTg5Y2QtYTAwMzNmMDU2ZDgxXkEyXkFqcGdeQXVyMjMxOTE0ODA@._V1_UX182_CR0,0,182,268_AL__QL100.jpg",
                "6.1"));
    }

    private static ClassModel createClassModel(String title, String desc, String photo, String rating){
        ClassModel returnModel;

        returnModel = new ClassModel();
        returnModel.setTitle(title);
        returnModel.setDescription(desc);
        returnModel.setPhoto(photo);
        returnModel.setRating(rating);

        return returnModel;
    }
}
