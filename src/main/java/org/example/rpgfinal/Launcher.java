package org.example.rpgfinal;

import javafx.application.Application;
import org.example.rpgfinal.dao.CharacterDao;
import org.example.rpgfinal.model.ability.*;
import org.example.rpgfinal.model.character.Character;
import org.example.rpgfinal.model.character.CharacterBuilder;
import org.example.rpgfinal.model.combat.*;
import org.example.rpgfinal.model.group.*;
import org.example.rpgfinal.model.rules.GameSettings;
import org.example.rpgfinal.model.validation.CharacterValidator;
import org.example.rpgfinal.model.validation.NameNotEmptyValidator;
import org.example.rpgfinal.model.validation.StatPointsValidator;
import org.example.rpgfinal.model.validation.UniqueNameValidator;

import java.util.Collections;

public class Launcher {

    public static void main(String[] args) {

        title("test des fonctionnalités");

        us11_builder_character_creation();
        us21_singleton_game_settings();
        us23_character_validation();
        us12_decorator_abilities();
        us13_dao_persistence();
        us14_composite_groups();
        us22_game_actions();
        us41_simple_combat();
        us43_combat_history();
        us31_mvc_stub();
        us32_observer_sync();
        us42_combat_journal();

        title("fin des test");

        Application.launch(MainApp.class, args);

    }

    // us 1.1 builder
    private static void us11_builder_character_creation() {
        title("us 1.1 builder");

        Character hero = new CharacterBuilder()
                .name("Juliano")
                .characterClass("warrior")
                .hp(100)
                .strength(30)
                .intelligence(20)
                .agility(25)
                .build();

        System.out.println(hero);
    }

    // us 2.1 singleton
    private static void us21_singleton_game_settings() {
        title("us 2.1 singleton");

        GameSettings settings = GameSettings.getInstance();
        System.out.println("max stats " + settings.getMaxStatPoints());
        System.out.println("max group size " + settings.getMaxCharactersPerGroup());
    }

    // us 2.3 validation
    private static void us23_character_validation() {
        title("us 2.3 validation");
        Character heroValid = createCharacter("naruto", "archer", 100, 30, 20, 10);
        Character heroTest = createCharacter("Bobby", "archer", 100, 50, 50, 0);

        String uniqueName = "tog";
        CharacterValidator validator1 = new NameNotEmptyValidator();
        CharacterValidator validator2 = new UniqueNameValidator(Collections.singleton(uniqueName));
        CharacterValidator validator3 = new StatPointsValidator();

        validator1.setNext(validator2);
        validator2.setNext(validator3);

        // Validation
        validator1.validate(heroValid);
        validator2.validate(heroValid);
        validator3.validate(heroValid);
    }

    // us 1.2 decorator
    private static void us12_decorator_abilities() {
        title("us 1.2 decorator");

        Character base = createCharacter("assassin", "rogue", 90, 35, 10, 40);
        CharacterComponent component = new BaseCharacterComponent(base);

        component = new InvisibilityAbility(component);
        component = new TelepathyAbility(component);

        System.out.println(component.getDescription());
    }

    // us 1.3 dao
    private static void us13_dao_persistence() {
        title("us 1.3 dao");

        CharacterDao dao = new CharacterDao();

        Character arthur = createCharacter("Arthur", "tank", 120, 40, 10, 10);
        Character benoit = createCharacter("Benoit", "mage", 80, 10, 50, 20);

        dao.save(arthur);
        dao.save(benoit);

        dao.findAll().forEach(System.out::println);

        dao.delete(arthur);
        dao.delete(benoit);
    }

    // us 1.4 composite
    private static void us14_composite_groups() {
        title("us 1.4 composite");

        CharacterComponent jude =
                new BaseCharacterComponent(createCharacter("Jude le Beau", "warrior", 100, 30, 20, 20));
        CharacterComponent wassim =
                new BaseCharacterComponent(createCharacter("Wassim le Vaillant", "mage", 80, 10, 50, 20));

        GroupComposite party = new GroupComposite("L’Escouade de l’Aube");
        party.add(new CharacterLeaf(jude));
        party.add(new CharacterLeaf(wassim));

        CharacterComponent rose =
                new BaseCharacterComponent(createCharacter("Rose la rose", "archer", 90, 20, 20, 40));
        CharacterComponent mark =
                new BaseCharacterComponent(createCharacter("Mark le maudit", "assassin", 85, 35, 15, 45));

        GroupComposite shadows = new GroupComposite("Les Lames Silencieuses");
        shadows.add(new CharacterLeaf(rose));
        shadows.add(new CharacterLeaf(mark));

        // Armée principale
        GroupComposite army = new GroupComposite("Les Gardiens du Royaume");
        army.add(party);
        army.add(shadows);

        army.display("");
    }


    // us 2.2 commands
    private static void us22_game_actions() {
        title("us 2.2 commands");

        CharacterComponent player =
                new BaseCharacterComponent(createCharacter("player", "rogue", 100, 30, 10, 40));
        CharacterComponent bot =
                new BaseCharacterComponent(createCharacter("bot", "warrior", 110, 35, 10, 15));

        CombatContext context = new CombatContext(player, bot);

        new AttackCombatCommand(CombatActor.PLAYER).execute(context);
        new BlockCombatCommand(CombatActor.BOT).execute(context);
        new PowerCombatCommand(CombatActor.PLAYER).execute(context);
    }

    // us 4.1 simple combat
    private static void us41_simple_combat() {
        title("us 4.1 simple combat");

        CharacterComponent idriss =
                new BaseCharacterComponent(createCharacter("Idriss", "paladin", 120, 35, 15, 20));
        CharacterComponent aymen =
                new BaseCharacterComponent(createCharacter("Aymen", "orc", 110, 30, 10, 15));

        System.out.println("Combattants :");
        System.out.println("- " + idriss.getDescription());
        System.out.println("- " + aymen.getDescription());

        CombatService service = new CombatService();
        CombatResult result = service.fight(idriss, aymen);

        System.out.println("\n le resultat du combat");
        System.out.println(result.getSummary());
    }

    // us 4.3 history
    private static void us43_combat_history() {
        title("us 4.3 history");

        CharacterComponent mathieu =
                new BaseCharacterComponent(createCharacter("Mathieu", "rogue", 90, 30, 10, 40));
        CharacterComponent margot =
                new BaseCharacterComponent(createCharacter("Margot", "knight", 110, 35, 10, 20));

        CombatService service = new CombatService();
        service.fight(mathieu, margot);
    }

    // us 3.1 mvc javafx
    private static void us31_mvc_stub() {
        title("us 3.1 mvc");

        System.out.println("lancer main app");
    }

    // us 3.2 observer
    private static void us32_observer_sync() {
        title("us 3.2 observer");

        BaseCharacterComponent hero =
                new BaseCharacterComponent(createCharacter("observer", "tank", 120, 40, 10, 10));

        hero.addHpListener((name, hp) ->
                System.out.println(name + " hp changed " + hp));

        hero.takeDamage(30);
        hero.heal(10);
    }

    // us 4.2 combat journal
    private static void us42_combat_journal() {
        title("us 4.2 journal");

        BaseCharacterComponent pierre =
                new BaseCharacterComponent(createCharacter("Pierre", "assassin", 100, 30, 10, 40));
        BaseCharacterComponent bastien =
                new BaseCharacterComponent(createCharacter("Bastien", "soldier", 100, 30, 10, 30));

        CombatService service = new CombatService();
        service.getObservable().addObserver(
                System.out::println
        );

        service.fight(pierre, bastien);
    }

    // helpers
    private static Character createCharacter(
            String name, String clazz, int hp, int str, int intel, int agi) {

        return new CharacterBuilder()
                .name(name)
                .characterClass(clazz)
                .hp(hp)
                .strength(str)
                .intelligence(intel)
                .agility(agi)
                .build();
    }

    private static void title(String text) {
        System.out.println("\n----------------------------");
        System.out.println(text);
        System.out.println("----------------------------");
    }

}