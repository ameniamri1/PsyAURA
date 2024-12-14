package psyaurafxml;

import java.util.*;

public class PsyAura {

    public static void main(String[] args) {
        Admin admin = new Admin(1, "Admin Principal", "admin@example.com");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuer = true;

            while (continuer) {
                System.out.println("\n=====================");
                System.out.println("Bienvenue à PsyAura !");
                System.out.println("=====================");
                System.out.println("1. Menu Utilisateur");
                System.out.println("2. Menu Administrateur");
                System.out.println("3. Quitter");
                System.out.print("Votre choix : ");

                try {
                    int choix = scanner.nextInt();

                    switch (choix) {
                        case 1 -> afficherMenuUtilisateur(scanner);
                        case 2 -> afficherMenuAdmin(admin ,scanner);
                        case 3 -> {
                            System.out.println("Merci d'avoir utilisé PsyAura. À bientôt !");
                            continuer = false;
                        }
                        default -> System.out.println("Choix invalide. Veuillez réessayer.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                    scanner.nextLine(); 
                }
            }
        }
    }

    private static void afficherMenuUtilisateur(Scanner scanner) {
        System.out.println("\nMenu Utilisateur :");
        System.out.println("1. Commencer un test de personnalité");
        System.out.println("2. Retour au menu principal");
        System.out.print("Votre choix : ");

        try {
            int choix = scanner.nextInt();

            switch (choix) {
                case 1 -> afficherMenuTests(scanner);
                case 2 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            scanner.nextLine();
        }
    }

private static void afficherMenuAdmin(Admin admin, Scanner scanner) {
    System.out.println("\nMenu Administrateur :");
    System.out.println("1. Gérer les tests (Ajouter, Modifier, Supprimer, Consulter)");
    System.out.println("2. Gérer les utilisateurs (fonctionnalité à implémenter)");
    System.out.println("3. Retour au menu principal");
    System.out.print("Votre choix : ");

    try {
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne restante

        switch (choix) {
            case 1 -> {
                afficherSousMenuTests(admin, scanner);
            }
            case 2 -> {
                System.out.println("Gestion des utilisateurs (fonctionnalité à implémenter).");
            }
            case 3 -> System.out.println("Retour au menu principal...");
            default -> System.out.println("Choix invalide. Veuillez réessayer.");
        }
    } catch (InputMismatchException e) {
        System.out.println("Entrée invalide. Veuillez entrer un nombre.");
        scanner.nextLine(); // Consommer la ligne restante
    }
}

private static void afficherSousMenuTests(Admin admin, Scanner scanner) {
    System.out.println("\nGestion des Tests :");
    System.out.println("1. Ajouter une question");
    System.out.println("2. Modifier une question");
    System.out.println("3. Supprimer une question");
    System.out.println("4. Consulter les questions");
    System.out.println("5. Retour au menu précédent");
    System.out.print("Votre choix : ");

    try {
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne restante

        switch (choix) {
            case 1 -> admin.ajouter();
            case 2 -> admin.modifier();
            case 3 -> admin.supprimer();
            case 4 -> admin.consulter();
            case 5 -> System.out.println("Retour au menu administrateur...");
            default -> System.out.println("Choix invalide. Veuillez réessayer.");
        }
    } catch (InputMismatchException e) {
        System.out.println("Entrée invalide. Veuillez entrer un nombre.");
        scanner.nextLine(); 
    }
}


    private static void afficherMenuTests(Scanner scanner) {
    System.out.println("\nChoisissez un type de test :");
    System.out.println("1. Optimisme");
    System.out.println("2. Pessimisme");
    System.out.println("3. Confiance");
    System.out.println("4. Dépression");
    System.out.print("Entrez le numéro de votre choix : ");

    try {
        int choixTest = scanner.nextInt();
        List<Question> questions = new ArrayList<>();
        String typePersonnalite = "";

        switch (choixTest) {
            case 1 -> {
                questions = creerTestOptimisme();
                typePersonnalite = "Optimisme";
            }
            case 2 -> {
                questions = creerTestPessimisme();
                typePersonnalite = "Pessimisme";
            }
            case 3 -> {
                questions = creerTestConfiance();
                typePersonnalite = "Confiance";
            }
            case 4 -> {
                questions = creerTestDepression();
                typePersonnalite = "Dépression";
            }
            default -> {
                System.out.println("Choix invalide.");
                return;
            }
        }

        TestPersonnalite test = new TestPersonnalite(questions);
        test.commencerTest();

        System.out.println("\nVotre score : " + test.getScore());

        String analyse = switch (choixTest) {
            case 1 -> analyserScoreOptimisme(test.getScore());
            case 2 -> analyserScorePessimisme(test.getScore());
            case 3 -> analyserScoreConfiance(test.getScore());
            case 4 -> analyserScoreDepression(test.getScore());
            default -> "Aucune analyse disponible.";
        };

        System.out.println("Analyse : " + analyse);

        TypePersonnalite personnalite = new TypePersonnalite(typePersonnalite);

        // Appel à genererDescription
        String description = personnalite.genererDescription(typePersonnalite, test.getScore());
        
        System.out.println("\nType de personnalité : " + personnalite.getType());
        System.out.println("Description : " + description);
    } catch (InputMismatchException e) {
        System.out.println("Entrée invalide. Veuillez entrer un nombre.");
        scanner.nextLine();
    }
}


    // Analyse pour le test de Dépression
    private static String analyserScoreDepression(int score) {
    if (score <= -9) {
        return "Dépression sévère";
    } else if (score <= -6) {
        return "Dépression modérée";
    } else if (score <= -4) {
        return "Dépression légère";
    } else {
        return "Pas de signes de dépression";
    }
}

private static String analyserScoreOptimisme(int score) {
    if (score >= 9) {
        return "Optimisme très faible";
    } else if (score >= 6) {
        return "Optimisme faible";
    } else if (score >= 4) {
        return "Optimisme modéré";
    } else {
        return "Optimisme élevé";
    }
}

private static String analyserScorePessimisme(int score) {
    if (score <= -9) {
        return "Pessimisme très élevé";
    } else if (score <= -6) {
        return "Pessimisme modéré";
    } else if (score <= -4) {
        return "Pessimisme faible";
    } else if (score <= 0) {
        return "Pessimisme faible";
    }
    else {
        return "Pas de signes de pessimisme";
    }
}

private static String analyserScoreConfiance(int score) {
    if (score >= 9) {
        return "Confiance très faible";
    } else if (score >= 6) {
        return "Confiance faible";
    } else if (score >= 4) {
        return "Confiance modérée";
    } else {
        return "Confiance élevée";
    }
}

    

    // Créer les 12 questions pour le test d'Optimisme
    private static List<Question> creerTestOptimisme() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Lorsque vous rencontrez un problème, vous êtes confiant(e) de pouvoir le résoudre.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous croyez que les choses finiront par s'améliorer, même après un échec.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous trouvez des opportunités même dans les situations difficiles.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous avez tendance à voir le verre à moitié plein.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous avez une attitude positive même lorsqu'il y a des obstacles.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous croyez qu'il y a toujours une lumière au bout du tunnel.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Les gens qui vous entourent vous décrivent comme optimiste.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous êtes enthousiaste à l'idée de relever de nouveaux défis.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Lorsque vous échouez, vous considérez cela comme un apprentissage.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous croyez que chaque jour est une nouvelle opportunité.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous vous concentrez sur ce qui peut être accompli, même dans des moments difficiles.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Votre attitude reste positive même quand les autres sont négatifs.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        return questions;
}


    // Créer les 12 questions pour le test de Pessimisme
    private static List<Question> creerTestPessimisme() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Lorsque vous rencontrez un problème, vous êtes confiant(e) de pouvoir le résoudre.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous croyez que les choses finiront par s'améliorer, même après un échec.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous trouvez des opportunités même dans les situations difficiles.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous avez tendance à voir le verre à moitié plein.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous avez une attitude positive même lorsqu'il y a des obstacles.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous croyez qu'il y a toujours une lumière au bout du tunnel.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Les gens qui vous entourent vous décrivent comme optimiste.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous êtes enthousiaste à l'idée de relever de nouveaux défis.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Lorsque vous échouez, vous considérez cela comme un apprentissage.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous croyez que chaque jour est une nouvelle opportunité.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous vous concentrez sur ce qui peut être accompli, même dans des moments difficiles.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Votre attitude reste positive même quand les autres sont négatifs.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        return questions;
    }

    // Créer les 12 questions pour le test de Confiance
    private static List<Question> creerTestConfiance() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Vous êtes à l'aise de prendre la parole en public.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous faites confiance à vos compétences pour réussir.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous aimez relever des défis, même si vous ne savez pas si vous réussirez.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Lorsque vous faites face à une situation inconnue, vous êtes calme et sûr(e) de vous.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Les autres vous disent souvent que vous inspirez confiance.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous êtes capable de prendre des décisions importantes sans hésiter.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Lorsque vous êtes confronté(e) à des difficultés, vous croyez que vous pouvez les surmonter.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous avez une attitude positive envers vos capacités.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous croyez que l'échec est une étape vers la réussite.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous vous sentez souvent capable d'influencer positivement votre environnement.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous êtes capable de défendre vos opinions et idées devant les autres.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        questions.add(new Question("Vous vous sentez à l'aise dans des situations sociales.", List.of("Oui", "Parfois", "Non"), List.of(1, 0, -1)));
        return questions;
}


    // Créer les 12 questions pour le test de Dépression
    private static List<Question> creerTestDepression() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Vous vous sentez souvent fatigué(e), même après une bonne nuit de sommeil.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous avez perdu de l'intérêt pour les activités que vous aimiez auparavant.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous avez des difficultés à vous lever le matin.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous vous sentez souvent inutile ou coupable.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous avez du mal à prendre des décisions, même les plus simples.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous vous sentez souvent triste sans raison apparente.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous avez des troubles de l'appétit ou du sommeil.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous vous sentez souvent seul(e), même entouré(e) d'autres personnes.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous avez du mal à trouver de la joie dans des moments agréables.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous avez souvent des pensées négatives sur vous-même.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous avez du mal à vous concentrer sur des tâches importantes.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        questions.add(new Question("Vous avez parfois pensé que la vie ne valait plus la peine d'être vécue.", List.of("Oui", "Parfois", "Non"), List.of(-1, 0, 1)));
        return questions;
}
}