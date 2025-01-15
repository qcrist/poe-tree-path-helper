package ggpk.generated;

import java.lang.annotation.*;
import java.util.*;

import ggpk.DatReader.*;

public class DatTypesV2 {
    public static class RogueExiles implements DatType{
        @Offset(0) public ForeignRow MonsterVarietiesKey; //foreignrow
    }
    public static class RogueExileLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int AdditionalLife; //i32
    }
    public static class Shrines implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int TimeoutInSeconds; //i32
        @Offset(12) public boolean ChargesShared; //bool
        @Offset(13) public ForeignRow Player_ShrineBuffsKey; //foreignrow
        @Offset(37) public ForeignRow Monster_ShrineBuffsKey; //foreignrow
        @Offset(53) public ForeignRow SummonMonster_MonsterVarietiesKey; //foreignrow
        @Offset(69) public ForeignRow SummonPlayer_MonsterVarietiesKey; //foreignrow
        @Offset(93) public ForeignRow ShrineSoundsKey; //foreignrow
        @Offset(110) public List<ForeignRow> AchievementItemsKeys; //foreignrow
        @Offset(126) public boolean IsPVPOnly; //bool
        @Offset(128) public boolean IsLesserShrine; //bool
        @Offset(129) public ForeignRow Description; //foreignrow
        @Offset(145) public ForeignRow Name; //foreignrow
    }
    public static class ShrineSounds implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String StereoSoundFile; //string
        @Offset(16) public String MonoSoundFile; //string
    }
    public static class Strongboxes implements DatType{
        @Offset(0) public ForeignRow ChestsKey; //foreignrow
        @Offset(16) public int SpawnWeight; //i32
        @Offset(26) public ForeignRow SpawnWeightIncrease; //foreignrow
        @Offset(42) public int SpawnWeightHardmode; //i32
    }
    public static class InvasionMonsterRestrictions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(24) public List<ForeignRow> MonsterVarietiesKeys; //foreignrow
    }
    public static class InvasionMonstersPerArea implements DatType{
        @Offset(0) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(36) public List<ForeignRow> MonsterVarietiesKeys1; //foreignrow
        @Offset(52) public List<ForeignRow> MonsterVarietiesKeys2; //foreignrow
    }
    public static class BeyondFactions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Boss; //foreignrow
    }
    public static class TormentSpirits implements DatType{
        @Offset(0) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(16) public List<ForeignRow> Spirit_ModsKeys; //foreignrow
        @Offset(32) public List<ForeignRow> Touched_ModsKeys; //foreignrow
        @Offset(48) public List<ForeignRow> Possessed_ModsKeys; //foreignrow
        @Offset(64) public int MinZoneLevel; //i32
        @Offset(68) public int MaxZoneLevel; //i32
        @Offset(72) public int SpawnWeight; //i32
        @Offset(76) public ForeignRow SummonedMonster_MonsterVarietiesKey; //foreignrow
        @Offset(96) public List<ForeignRow> ModsKeys0; //foreignrow
        @Offset(112) public List<ForeignRow> ModsKeys1; //foreignrow
    }
    public static class DivinationCardArt implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public String VirtualFile; //string
        @Offset(24) public List<Integer> Influences; //enumrow
    }
    public static class WarbandsGraph implements DatType{
        @Offset(0) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(16) public List<Integer> Connections; //i32
    }
    public static class WarbandsMapGraph implements DatType{
        @Offset(0) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(16) public List<Integer> Connections; //i32
    }
    public static class WarbandsPackMonsters implements DatType{
        @Offset(0) public String Id; //string
        @Offset(20) public List<ForeignRow> Tier4_MonsterVarietiesKeys; //foreignrow
        @Offset(36) public List<ForeignRow> Tier3_MonsterVarietiesKeys; //foreignrow
        @Offset(52) public List<ForeignRow> Tier2_MonsterVarietiesKeys; //foreignrow
        @Offset(68) public List<ForeignRow> Tier1_MonsterVarietiesKeys; //foreignrow
        @Offset(84) public String Tier1Name; //string
        @Offset(92) public String Tier2Name; //string
        @Offset(100) public String Tier3Name; //string
        @Offset(108) public String Tier4Name; //string
        @Offset(116) public String Tier1Art; //string
        @Offset(124) public String Tier2Art; //string
        @Offset(132) public String Tier3Art; //string
        @Offset(140) public String Tier4Art; //string
    }
    public static class WarbandsPackNumbers implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int SpawnChance; //i32
        @Offset(12) public int MinLevel; //i32
        @Offset(16) public int MaxLevel; //i32
        @Offset(20) public int Tier4Number; //i32
        @Offset(28) public int Tier3Number; //i32
        @Offset(36) public int Tier2Number; //i32
        @Offset(44) public int Tier1Number; //i32
    }
    public static class TalismanMonsterMods implements DatType{
        @Offset(0) public ForeignRow ModTypeKey; //foreignrow
        @Offset(16) public ForeignRow ModsKey; //foreignrow
    }
    public static class TalismanPacks implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> MonsterPacksKeys; //foreignrow
        @Offset(32) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(76) public ForeignRow MonsterPacksKey; //foreignrow
    }
    public static class Talismans implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public int SpawnWeight; //i32
        @Offset(20) public ForeignRow ModsKey; //foreignrow
        @Offset(36) public int Tier; //i32
    }
    public static class LabyrinthAreas implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> Normal_WorldAreasKeys; //foreignrow
        @Offset(24) public List<ForeignRow> Cruel_WorldAreasKeys; //foreignrow
        @Offset(40) public List<ForeignRow> Merciless_WorldAreasKeys; //foreignrow
        @Offset(56) public List<ForeignRow> Endgame_WorldAreasKeys; //foreignrow
    }
    public static class LabyrinthBonusItems implements DatType{
        @Offset(0) public ForeignRow BaseItemType; //foreignrow
        @Offset(16) public int AreaLevel; //i32
        @Offset(20) public String LabyrinthName; //string
    }
    public static class LabyrinthExclusionGroups implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class LabyrinthIzaroChests implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow ChestsKey; //foreignrow
        @Offset(24) public int SpawnWeight; //i32
        @Offset(28) public int MinLabyrinthTier; //i32
        @Offset(32) public int MaxLabyrinthTier; //i32
    }
    public static class LabyrinthNodeOverrides implements DatType{
        @Offset(0) public String Id1; //string
        @Offset(8) public String Id2; //string
    }
    public static class LabyrinthRewardTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String ObjectPath; //string
    }
    public static class Labyrinths implements DatType{
        @Offset(0) public int Tier; //i32
        @Offset(4) public String Name; //string
        @Offset(12) public ForeignRow OfferingItem; //foreignrow
        @Offset(28) public ForeignRow QuestFlag; //foreignrow
        @Offset(44) public List<ForeignRow> RequiredTrials; //foreignrow
        @Offset(60) public int AreaLevel; //i32
        @Offset(68) public ForeignRow JewelReward; //foreignrow
        @Offset(116) public int MinLevel; //i32
        @Offset(128) public ForeignRow CraftingFontDescription; //foreignrow
    }
    public static class LabyrinthSecretEffects implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(24) public ForeignRow Buff_BuffDefinitionsKey; //foreignrow
        @Offset(40) public List<Integer> Buff_StatValues; //i32
        @Offset(56) public String OTFile; //string
    }
    public static class LabyrinthSecrets implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Id2; //string
        @Offset(40) public List<ForeignRow> LabyrinthSecretEffectsKeys0; //foreignrow
        @Offset(56) public List<ForeignRow> LabyrinthSecretEffectsKeys1; //foreignrow
        @Offset(72) public List<ForeignRow> LabyrinthSecretEffectsKeys2; //foreignrow
        @Offset(92) public List<ForeignRow> LabyrinthSecretEffectsKeys3; //foreignrow
        @Offset(117) public String Name; //string
        @Offset(125) public ForeignRow AchievementItemsKey; //foreignrow
        @Offset(141) public int LabyrinthTierMinimum; //i32
        @Offset(145) public int LabyrinthTierMaximum; //i32
    }
    public static class LabyrinthSection implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow ExclusionGroup; //foreignrow
    }
    public static class LabyrinthSectionLayout implements DatType{
        @Offset(0) public ForeignRow LabyrinthSectionKey; //foreignrow
        @Offset(20) public List<Long> LabyrinthSectionLayoutKeys; //row
        @Offset(36) public ForeignRow LabyrinthSecretsKey0; //foreignrow
        @Offset(52) public ForeignRow LabyrinthSecretsKey1; //foreignrow
        @Offset(68) public ForeignRow LabyrinthAreasKey; //foreignrow
        @Offset(84) public float Float0; //f32
        @Offset(88) public float Float1; //f32
        @Offset(92) public List<ForeignRow> LabyrinthNodeOverridesKeys; //foreignrow
    }
    public static class LabyrinthTrials implements DatType{
        @Offset(0) public ForeignRow WorldAreas; //foreignrow
        @Offset(28) public ForeignRow NPCTextAudioKey; //foreignrow
    }
    public static class LabyrinthTrinkets implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public List<ForeignRow> LabyrinthSecretsKey; //foreignrow
        @Offset(32) public ForeignRow Buff_BuffDefinitionsKey; //foreignrow
        @Offset(48) public List<Integer> Buff_StatValues; //i32
    }
    public static class LabyrinthCraftOptions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int CraftFamily; //enumrow
        @Offset(12) public String Text; //string
        @Offset(20) public String Script; //string
        @Offset(28) public String ScriptArgument; //string
        @Offset(36) public short HASH16; //i16
        @Offset(39) public ForeignRow Tier; //foreignrow
        @Offset(56) public List<ForeignRow> Achievement; //foreignrow
    }
    public static class LabyrinthCraftOptionTiers implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String CraftIcon; //string
    }
    public static class ShaperGuardians implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow WorldArea; //foreignrow
    }
    public static class Essences implements DatType{
        @Offset(0) public ForeignRow BaseItemType; //foreignrow
        @Offset(16) public int HASH32; //i32
        @Offset(48) public ForeignRow MonsterMod; //foreignrow
        @Offset(65) public ForeignRow CraftTag; //foreignrow
    }
    public static class EssenceType implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int EssenceType; //i32
        @Offset(12) public boolean IsCorruptedEssence; //bool
        @Offset(13) public ForeignRow WordsKey; //foreignrow
    }
    public static class BreachBossLifeScalingPerLevel implements DatType{
        @Offset(0) public int MonsterLevel; //i32
        @Offset(4) public int LifeMultiplier; //i32
    }
    public static class BreachElement implements DatType{
        @Offset(0) public String Element; //string
        @Offset(24) public ForeignRow BaseBreachstone; //foreignrow
        @Offset(40) public ForeignRow BossMapMod; //foreignrow
        @Offset(56) public ForeignRow DuplicateBoss; //foreignrow
    }
    public static class Harbingers implements DatType{
        @Offset(0) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(20) public int MinLevel; //i32
        @Offset(24) public int MaxLevel; //i32
    }
    public static class PantheonPanelLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int X; //i32
        @Offset(12) public int Y; //i32
        @Offset(16) public boolean IsMajorGod; //bool
        @Offset(17) public String CoverImage; //string
        @Offset(25) public String GodName2; //string
        @Offset(33) public String SelectionImage; //string
        @Offset(41) public List<ForeignRow> Effect1_StatsKeys; //foreignrow
        @Offset(57) public List<Integer> Effect1_Values; //i32
        @Offset(73) public List<ForeignRow> Effect2_StatsKeys; //foreignrow
        @Offset(89) public String GodName3; //string
        @Offset(97) public List<Integer> Effect3_Values; //i32
        @Offset(113) public List<ForeignRow> Effect3_StatsKeys; //foreignrow
        @Offset(129) public String GodName4; //string
        @Offset(137) public List<ForeignRow> Effect4_StatsKeys; //foreignrow
        @Offset(153) public List<Integer> Effect4_Values; //i32
        @Offset(169) public String GodName1; //string
        @Offset(177) public List<Integer> Effect2_Values; //i32
        @Offset(193) public ForeignRow QuestFlag; //foreignrow
        @Offset(209) public List<ForeignRow> AchievementItems; //foreignrow
        @Offset(225) public int LeagueQuestFlag1; //i32
        @Offset(229) public int LeagueQuestFlag2; //i32
        @Offset(233) public int LeagueQuestFlag3; //i32
        @Offset(237) public int LeagueQuestFlag4; //i32
        @Offset(258) public List<ForeignRow> DowngradeFlags; //foreignrow
    }
    public static class PantheonSouls implements DatType{
        @Offset(0) public ForeignRow WorldArea; //foreignrow
        @Offset(16) public ForeignRow CapturedVessel; //foreignrow
        @Offset(32) public ForeignRow QuestFlagUpgrade; //foreignrow
        @Offset(48) public List<ForeignRow> CapturedMonster; //foreignrow
        @Offset(64) public ForeignRow PanelLayout; //foreignrow
        @Offset(80) public String CapturedMonsterDescription; //string
        @Offset(92) public ForeignRow QuestFlagDowngrade; //foreignrow
    }
    public static class AbyssObjects implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int MinLevel; //i32
        @Offset(12) public int MaxLevel; //i32
        @Offset(16) public int SpawnWeight; //i32
        @Offset(24) public String MetadataFile; //string
        @Offset(36) public List<ForeignRow> DaemonSpawners; //foreignrow
        @Offset(60) public ForeignRow AbyssalDepths; //foreignrow
    }
    public static class ElderBossArenas implements DatType{
        @Offset(0) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(20) public List<ForeignRow> AchievementItemsKeys; //foreignrow
    }
    public static class ElderMapBossOverride implements DatType{
        @Offset(0) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(16) public List<ForeignRow> MonsterVarietiesKeys; //foreignrow
        @Offset(32) public String TerrainMetadata; //string
    }
    public static class ElderGuardians implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String MapIcon; //string
    }
    public static class BestiaryCapturableMonsters implements DatType{
        @Offset(0) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(16) public ForeignRow BestiaryGroupsKey; //foreignrow
        @Offset(32) public ForeignRow BestiaryEncountersKey; //foreignrow
        @Offset(49) public String IconSmall; //string
        @Offset(57) public String Icon; //string
        @Offset(65) public ForeignRow Boss_MonsterVarietiesKey; //foreignrow
        @Offset(81) public ForeignRow BestiaryGenusKey; //foreignrow
        @Offset(98) public long BestiaryCapturableMonstersKey; //row
        @Offset(106) public boolean IsDisabled; //bool
    }
    public static class BestiaryEncounters implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MonsterPacksKey; //foreignrow
        @Offset(24) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(40) public String MonsterSpawnerId; //string
    }
    public static class BestiaryFamilies implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public String Icon; //string
        @Offset(24) public String IconSmall; //string
        @Offset(32) public String Illustration; //string
        @Offset(40) public String PageArt; //string
        @Offset(48) public String FlavourText; //string
        @Offset(57) public ForeignRow TagsKey; //foreignrow
        @Offset(77) public List<ForeignRow> ModsKeys; //foreignrow
        @Offset(93) public ForeignRow CurrencyItemsKey; //foreignrow
    }
    public static class BestiaryGenus implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public ForeignRow BestiaryGroupsKey; //foreignrow
        @Offset(32) public String Name2; //string
        @Offset(40) public String Icon; //string
    }
    public static class BestiaryGroups implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
        @Offset(16) public String Illustration; //string
        @Offset(24) public String Name; //string
        @Offset(32) public String Icon; //string
        @Offset(40) public String IconSmall; //string
        @Offset(48) public ForeignRow BestiaryFamiliesKey; //foreignrow
        @Offset(64) public List<ForeignRow> AchievementItemsKeys; //foreignrow
    }
    public static class BestiaryNets implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(20) public int CaptureMinLevel; //i32
        @Offset(24) public int CaptureMaxLevel; //i32
        @Offset(28) public int DropMinLevel; //i32
        @Offset(32) public int DropMaxLevel; //i32
        @Offset(40) public boolean IsEnabled; //bool
    }
    public static class BestiaryRecipeComponent implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int MinLevel; //i32
        @Offset(12) public ForeignRow BestiaryFamiliesKey; //foreignrow
        @Offset(28) public ForeignRow BestiaryGroupsKey; //foreignrow
        @Offset(44) public ForeignRow ModsKey; //foreignrow
        @Offset(60) public ForeignRow BestiaryCapturableMonstersKey; //foreignrow
        @Offset(76) public ForeignRow BeastRarity; //foreignrow
        @Offset(92) public ForeignRow BestiaryGenusKey; //foreignrow
    }
    public static class BestiaryRecipeCategories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
    }
    public static class BestiaryRecipes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
        @Offset(16) public List<ForeignRow> BestiaryRecipeComponentKeys; //foreignrow
        @Offset(32) public String Notes; //string
        @Offset(40) public ForeignRow Category; //foreignrow
        @Offset(57) public List<ForeignRow> Achievements; //foreignrow
        @Offset(82) public int GameMode; //i32
        @Offset(86) public ForeignRow FlaskMod; //foreignrow
    }
    public static class NetTiers implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public int Tier; //i32
    }
    public static class ArchitectLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int MoreLife; //i32
    }
    public static class IncursionArchitect implements DatType{
        @Offset(0) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(16) public int MinLevel; //i32
    }
    public static class IncursionBrackets implements DatType{
        @Offset(0) public int MinLevel; //i32
        @Offset(4) public ForeignRow Incursion_WorldAreasKey; //foreignrow
        @Offset(20) public ForeignRow Template_WorldAreasKey; //foreignrow
    }
    public static class IncursionChestRewards implements DatType{
        @Offset(0) public ForeignRow IncursionRoomsKey; //foreignrow
        @Offset(16) public List<ForeignRow> IncursionChestsKeys; //foreignrow
        @Offset(32) public String ChestMarkerMetadata; //string
    }
    public static class IncursionChests implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow ChestsKey; //foreignrow
        @Offset(24) public ForeignRow UniqueChestsKey; //foreignrow
        @Offset(40) public int MinLevel; //i32
        @Offset(44) public int MaxLevel; //i32
        @Offset(48) public int Weight; //i32
    }
    public static class IncursionRoomBossFightEvents implements DatType{
        @Offset(0) public ForeignRow Room; //foreignrow
    }
    public static class IncursionRooms implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public int Tier; //i32
        @Offset(20) public int MinLevel; //i32
        @Offset(24) public long RoomUpgrade_IncursionRoomsKey; //row
        @Offset(32) public List<ForeignRow> Mods; //foreignrow
        @Offset(48) public String PresentARMFile; //string
        @Offset(56) public int HASH16; //i32
        @Offset(60) public ForeignRow IncursionArchitectKey; //foreignrow
        @Offset(76) public String PastARMFile; //string
        @Offset(84) public String TSIFile; //string
        @Offset(92) public String UIIcon; //string
        @Offset(100) public String FlavourText; //string
        @Offset(108) public String Description; //string
        @Offset(116) public List<ForeignRow> AchievementItemsKeys; //foreignrow
        @Offset(140) public long RoomUpgradeFrom_IncursionRoomsKey; //row
        @Offset(148) public ForeignRow ItemisedFlavourText; //foreignrow
    }
    public static class IncursionUniqueUpgradeComponents implements DatType{
        @Offset(0) public ForeignRow BaseUnique; //foreignrow
        @Offset(16) public ForeignRow UpgradeCurrency; //foreignrow
    }
    public static class DelveAzuriteShop implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public int SpawnWeight; //i32
        @Offset(20) public int Cost; //i32
        @Offset(24) public int MinDepth; //i32
        @Offset(28) public boolean IsResonator; //bool
    }
    public static class DelveBiomes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public List<ForeignRow> WorldAreasKeys; //foreignrow
        @Offset(32) public String UIImage; //string
        @Offset(40) public List<Integer> SpawnWeight_Depth; //i32
        @Offset(56) public List<Integer> SpawnWeight_Values; //i32
        @Offset(104) public String Art2D; //string
        @Offset(112) public List<ForeignRow> AchievementItemsKeys; //foreignrow
    }
    public static class DelveCatchupDepths implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int Depth; //i32
    }
    public static class DelveCraftingModifierDescriptions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
    }
    public static class DelveCraftingModifiers implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public List<ForeignRow> AddedModsKeys; //foreignrow
        @Offset(32) public List<ForeignRow> NegativeWeight_TagsKeys; //foreignrow
        @Offset(48) public List<Integer> NegativeWeight_Values; //i32
        @Offset(64) public List<ForeignRow> ForcedAddModsKeys; //foreignrow
        @Offset(80) public List<ForeignRow> ForbiddenDelveCraftingTagsKeys; //foreignrow
        @Offset(96) public List<ForeignRow> AllowedDelveCraftingTagsKeys; //foreignrow
        @Offset(112) public boolean CanMirrorItem; //bool
        @Offset(113) public int CorruptedEssenceChance; //i32
        @Offset(117) public boolean CanImproveQuality; //bool
        @Offset(118) public boolean HasLuckyRolls; //bool
        @Offset(120) public List<ForeignRow> SellPrice_ModsKeys; //foreignrow
        @Offset(136) public boolean CanRollWhiteSockets; //bool
        @Offset(137) public List<ForeignRow> Weight_TagsKeys; //foreignrow
        @Offset(153) public List<Integer> Weight_Values; //i32
        @Offset(169) public List<ForeignRow> DelveCraftingModifierDescriptionsKeys; //foreignrow
        @Offset(185) public List<ForeignRow> BlockedDelveCraftingModifierDescriptionsKeys; //foreignrow
    }
    public static class DelveCraftingTags implements DatType{
        @Offset(0) public ForeignRow TagsKey; //foreignrow
        @Offset(16) public String ItemClass; //string
    }
    public static class DelveDynamite implements DatType{
        @Offset(4) public ForeignRow ProjectilesKey; //foreignrow
        @Offset(36) public ForeignRow Dynamite_MiscObjectsKey; //foreignrow
        @Offset(80) public ForeignRow MiscAnimatedKey; //foreignrow
    }
    public static class DelveFeatures implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public List<Integer> SpawnWeight; //i32
        @Offset(32) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(48) public String Image; //string
        @Offset(56) public List<ForeignRow> AchievementItemsKeys; //foreignrow
        @Offset(88) public List<Integer> MinDepth; //i32
        @Offset(104) public String Description; //string
    }
    public static class DelveFlares implements DatType{
    }
    public static class DelveLevelScaling implements DatType{
        @Offset(0) public int Depth; //i32
        @Offset(4) public int MonsterLevel; //i32
        @Offset(12) public int SulphiteCost; //i32
        @Offset(16) public int MonsterLevel2; //i32
        @Offset(20) public int MoreMonsterDamage; //i32
        @Offset(24) public int MoreMonsterLife; //i32
        @Offset(28) public int DarknessResistance; //i32
        @Offset(32) public int LightRadius; //i32
    }
    public static class DelveMonsterSpawners implements DatType{
        @Offset(0) public String BaseMetadata; //string
        @Offset(108) public String Script; //string
    }
    public static class DelveResourcePerLevel implements DatType{
        @Offset(0) public int AreaLevel; //i32
        @Offset(4) public int Sulphite; //i32
    }
    public static class DelveRewardTierConstants implements DatType{
    }
    public static class DelveRooms implements DatType{
        @Offset(0) public ForeignRow DelveBiomesKey; //foreignrow
        @Offset(16) public ForeignRow DelveFeaturesKey; //foreignrow
        @Offset(32) public String ARMFile; //string
    }
    public static class DelveUpgrades implements DatType{
        @Offset(0) public int DelveUpgradeTypeKey; //i32
        @Offset(4) public int UpgradeLevel; //i32
        @Offset(8) public List<ForeignRow> StatsKeys; //foreignrow
        @Offset(24) public List<Integer> StatValues; //i32
        @Offset(40) public int Cost; //i32
        @Offset(48) public ForeignRow AchievementItemsKey; //foreignrow
    }
    public static class BetrayalChoiceActions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BetrayalChoicesKey; //foreignrow
        @Offset(24) public ForeignRow ClientStringsKey; //foreignrow
    }
    public static class BetrayalChoices implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(20) public List<ForeignRow> Achievements; //foreignrow
    }
    public static class BetrayalDialogue implements DatType{
        @Offset(40) public ForeignRow BetrayalTargetsKey; //foreignrow
        @Offset(92) public ForeignRow BetrayalUpgradesKey; //foreignrow
        @Offset(158) public ForeignRow NPCTextAudioKey; //foreignrow
    }
    public static class BetrayalForts implements DatType{
        @Offset(0) public String Id; //string
        @Offset(20) public ForeignRow ExtraTerrainFeaturesKey; //foreignrow
    }
    public static class BetrayalJobs implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(16) public ForeignRow ExtraTerrainFeaturesKey; //foreignrow
        @Offset(32) public String Art; //string
        @Offset(48) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(64) public List<ForeignRow> Completion_AchievementItemsKey; //foreignrow
        @Offset(80) public List<ForeignRow> OpenChests_AchievementItemsKey; //foreignrow
        @Offset(96) public List<ForeignRow> MissionCompletion_AcheivementItemsKey; //foreignrow
    }
    public static class BetrayalRanks implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(16) public int Level; //i32
        @Offset(20) public String RankImage; //string
    }
    public static class BetrayalRelationshipState implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
    }
    public static class BetrayalTargetJobAchievements implements DatType{
        @Offset(0) public ForeignRow BetrayalTargetsKey; //foreignrow
        @Offset(16) public ForeignRow BetrayalJobsKey; //foreignrow
        @Offset(32) public ForeignRow AchievementItemsKey; //foreignrow
    }
    public static class BetrayalTargetLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int MoreLife; //i32
    }
    public static class BetrayalTargets implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BetrayalRanksKey; //foreignrow
        @Offset(24) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(40) public ForeignRow BetrayalJobsKey; //foreignrow
        @Offset(56) public String Art; //string
        @Offset(65) public ForeignRow ItemClasses; //foreignrow
        @Offset(81) public String FullName; //string
        @Offset(89) public String Safehouse_ARMFile; //string
        @Offset(97) public String ShortName; //string
        @Offset(109) public ForeignRow SafehouseLeader_AcheivementItemsKey; //foreignrow
        @Offset(125) public ForeignRow Level3_AchievementItemsKey; //foreignrow
        @Offset(169) public String ScriptArgument; //string
    }
    public static class BetrayalTraitorRewards implements DatType{
        @Offset(0) public ForeignRow BetrayalJobsKey; //foreignrow
        @Offset(16) public ForeignRow BetrayalTargetsKey; //foreignrow
        @Offset(32) public ForeignRow BetrayalRanksKey; //foreignrow
        @Offset(48) public String Description; //string
        @Offset(56) public String Description2; //string
    }
    public static class BetrayalUpgrades implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public String Description; //string
        @Offset(24) public List<ForeignRow> ModsKey; //foreignrow
        @Offset(40) public String ArtFile; //string
        @Offset(48) public int BetrayalUpgradeSlotsKey; //i32
        @Offset(68) public ForeignRow ItemVisualIdentityKey0; //foreignrow
        @Offset(84) public ForeignRow ItemVisualIdentityKey1; //foreignrow
        @Offset(100) public ForeignRow GrantedEffectsKey; //foreignrow
        @Offset(120) public ForeignRow ItemClassesKey; //foreignrow
    }
    public static class BetrayalWallLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int MoreLife; //i32
    }
    public static class SafehouseBYOCrafting implements DatType{
        @Offset(0) public ForeignRow BetrayalJobsKey; //foreignrow
        @Offset(16) public ForeignRow BetrayalTargetsKey; //foreignrow
        @Offset(32) public int Rank; //i32
        @Offset(36) public String Description; //string
        @Offset(44) public String ServerCommand; //string
        @Offset(68) public String Description2; //string
        @Offset(76) public String ServerCommand2; //string
    }
    public static class SafehouseCraftingSpreeType implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> Currencies; //foreignrow
        @Offset(24) public List<Integer> CurrencyCount; //i32
        @Offset(56) public boolean Disabled; //bool
        @Offset(57) public String ItemClassText; //string
    }
    public static class SafehouseCraftingSpreeCurrencies implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(24) public boolean HasSpecificBaseItem; //bool
    }
    public static class Scarabs implements DatType{
        @Offset(0) public ForeignRow Type; //foreignrow
        @Offset(16) public List<ForeignRow> Items; //foreignrow
    }
    public static class ScarabTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Tag; //foreignrow
        @Offset(24) public ForeignRow DisableDrops; //foreignrow
        @Offset(40) public ForeignRow MoreLikely; //foreignrow
        @Offset(56) public ForeignRow Count; //foreignrow
    }
    public static class SynthesisAreas implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int MinLevel; //i32
        @Offset(12) public int MaxLevel; //i32
        @Offset(16) public int Weight; //i32
        @Offset(20) public ForeignRow TopologiesKey; //foreignrow
        @Offset(36) public List<ForeignRow> MonsterPacksKeys; //foreignrow
        @Offset(52) public String ArtFile; //string
        @Offset(60) public String Name; //string
        @Offset(68) public ForeignRow SynthesisAreaSizeKey; //foreignrow
        @Offset(84) public ForeignRow AchievementItemsKey; //foreignrow
    }
    public static class SynthesisAreaSize implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class SynthesisBonuses implements DatType{
        @Offset(0) public ForeignRow ModsKey; //foreignrow
    }
    public static class SynthesisBrackets implements DatType{
        @Offset(0) public ForeignRow WorldAreasKey1; //foreignrow
        @Offset(16) public int MinLevel; //i32
        @Offset(20) public int MaxLevel; //i32
        @Offset(24) public ForeignRow WorldAreasKey2; //foreignrow
    }
    public static class SynthesisFragmentDialogue implements DatType{
        @Offset(16) public ForeignRow NPCTextAudioKey1; //foreignrow
        @Offset(32) public ForeignRow NPCTextAudioKey2; //foreignrow
        @Offset(48) public ForeignRow NPCTextAudioKey3; //foreignrow
        @Offset(64) public ForeignRow NPCTextAudioKey4; //foreignrow
        @Offset(80) public ForeignRow NPCTextAudioKey5; //foreignrow
        @Offset(96) public ForeignRow NPCTextAudioKey6; //foreignrow
    }
    public static class SynthesisGlobalMods implements DatType{
        @Offset(0) public ForeignRow ModsKey; //foreignrow
        @Offset(16) public int Weight; //i32
        @Offset(20) public int MinLevel; //i32
        @Offset(24) public int MaxLevel; //i32
    }
    public static class SynthesisMonsterExperiencePerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int ExperienceBonus; //i32
    }
    public static class SynthesisRewardCategories implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class SynthesisRewardTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
        @Offset(16) public String ArtFile; //string
        @Offset(24) public ForeignRow AchievementItemsKey; //foreignrow
    }
    public static class Incubators implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(20) public String Description; //string
        @Offset(28) public int HASH16; //i32
        @Offset(32) public List<ForeignRow> AchievementItemsKeys; //foreignrow
    }
    public static class LegionBalancePerLevel implements DatType{
        @Offset(0) public int MinLevel; //i32
    }
    public static class LegionChestTypes implements DatType{
        @Offset(16) public ForeignRow Chest; //foreignrow
        @Offset(32) public ForeignRow HardmodeChest; //foreignrow
        @Offset(52) public ForeignRow Faction; //foreignrow
    }
    public static class LegionChestCounts implements DatType{
        @Offset(0) public ForeignRow LegionFactionsKey; //foreignrow
        @Offset(16) public ForeignRow LegionRanksKey; //foreignrow
        @Offset(48) public int MinLevel; //i32
    }
    public static class LegionFactions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int SpawnWeight; //i32
        @Offset(12) public ForeignRow LegionBalancePerLevelKey; //foreignrow
        @Offset(36) public ForeignRow BuffVisualsKey; //foreignrow
        @Offset(52) public ForeignRow MiscAnimatedKey1; //foreignrow
        @Offset(68) public ForeignRow MiscAnimatedKey2; //foreignrow
        @Offset(84) public ForeignRow MiscAnimatedKey3; //foreignrow
        @Offset(100) public List<ForeignRow> AchievementItemsKeys1; //foreignrow
        @Offset(116) public ForeignRow MiscAnimatedKey4; //foreignrow
        @Offset(132) public ForeignRow MiscAnimatedKey5; //foreignrow
        @Offset(156) public List<ForeignRow> AchievementItemsKeys2; //foreignrow
        @Offset(172) public ForeignRow StatsKey; //foreignrow
        @Offset(188) public String Shard; //string
        @Offset(196) public String RewardJewelArt; //string
    }
    public static class LegionMonsterCounts implements DatType{
        @Offset(0) public ForeignRow LegionFactionsKey; //foreignrow
        @Offset(16) public ForeignRow LegionRanksKey; //foreignrow
    }
    public static class LegionMonsterVarieties implements DatType{
        @Offset(0) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(16) public ForeignRow LegionFactionsKey; //foreignrow
        @Offset(32) public ForeignRow LegionRanksKey; //foreignrow
        @Offset(52) public List<ForeignRow> MiscAnimatedKey; //foreignrow
        @Offset(196) public ForeignRow MonsterVarietiesKey2; //foreignrow
    }
    public static class LegionRanks implements DatType{
        @Offset(20) public ForeignRow LegionBalancePerLevelKey; //foreignrow
    }
    public static class LegionRewardTypeVisuals implements DatType{
        @Offset(0) public int IntId; //i32
        @Offset(4) public ForeignRow MinimapIconsKey; //foreignrow
        @Offset(28) public ForeignRow MiscAnimatedKey; //foreignrow
        @Offset(48) public String Id; //string
    }
    public static class BlightBalancePerLevel implements DatType{
        @Offset(0) public int Level; //i32
    }
    public static class BlightBossLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int MoreLife; //i32
    }
    public static class BlightChestTypes implements DatType{
        @Offset(0) public ForeignRow ChestsKey; //foreignrow
    }
    public static class BlightCraftingItems implements DatType{
        @Offset(0) public ForeignRow Oil; //foreignrow
        @Offset(16) public int Tier; //i32
        @Offset(20) public List<ForeignRow> Achievements; //foreignrow
        @Offset(36) public int UseType; //i32
        @Offset(40) public String NameShort; //string
    }
    public static class BlightCraftingRecipes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BlightCraftingResultsKey; //foreignrow
        @Offset(24) public List<ForeignRow> BlightCraftingItemsKeys; //foreignrow
    }
    public static class BlightCraftingResults implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow ModsKey; //foreignrow
        @Offset(24) public ForeignRow PassiveSkillsKey; //foreignrow
    }
    public static class BlightCraftingTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class BlightCraftingUniques implements DatType{
        @Offset(0) public ForeignRow WordsKey; //foreignrow
    }
    public static class BlightedSporeAuras implements DatType{
        @Offset(0) public ForeignRow BuffDefinitionsKey; //foreignrow
        @Offset(16) public List<Integer> BuffStatValues; //i32
    }
    public static class BlightEncounterTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Icon; //string
        @Offset(16) public boolean IsGeneric; //bool
        @Offset(17) public int Weight; //i32
    }
    public static class BlightEncounterWaves implements DatType{
        @Offset(0) public String MonsterSpawnerId; //string
        @Offset(8) public ForeignRow EncounterType; //foreignrow
        @Offset(36) public int Wave; //i32
    }
    public static class BlightRewardTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Icon; //string
    }
    public static class BlightTopologies implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BlightTopologyNodesKey; //foreignrow
    }
    public static class BlightTopologyNodes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(24) public int Size; //i32
        @Offset(28) public int Angle; //i32
    }
    public static class BlightTowerAuras implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public ForeignRow BuffDefinitionsKey; //foreignrow
        @Offset(24) public ForeignRow MiscAnimatedKey; //foreignrow
    }
    public static class BlightTowers implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public String Description; //string
        @Offset(24) public String Icon; //string
        @Offset(32) public List<Long> NextUpgradeOptions; //row
        @Offset(52) public String Tier; //string
        @Offset(60) public int Radius; //i32
        @Offset(68) public ForeignRow SpendResourceAchievement; //foreignrow
        @Offset(84) public ForeignRow StatsKey; //foreignrow
        @Offset(100) public List<ForeignRow> StatsKeys; //foreignrow
        @Offset(116) public List<ForeignRow> StatsKeys2; //foreignrow
    }
    public static class BlightTowersPerLevel implements DatType{
        @Offset(0) public ForeignRow BlightTowersKey; //foreignrow
        @Offset(20) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(36) public int Cost; //i32
    }
    public static class AtlasExileBossArenas implements DatType{
        @Offset(0) public ForeignRow Conqueror; //foreignrow
        @Offset(16) public ForeignRow WorldArea; //foreignrow
    }
    public static class AtlasExileInfluence implements DatType{
        @Offset(0) public ForeignRow Conqueror; //foreignrow
        @Offset(16) public List<ForeignRow> Sets; //foreignrow
    }
    public static class AtlasExiles implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public ForeignRow InfluencedItemIncrStat; //foreignrow
        @Offset(28) public String MapIcon; //string
        @Offset(36) public String MapIcon2; //string
    }
    public static class AlternateQualityTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
        @Offset(16) public ForeignRow Item; //foreignrow
        @Offset(48) public short HASH16; //i16
    }
    public static class AfflictionBalancePerLevel implements DatType{
    }
    public static class AfflictionEndgameWaveMods implements DatType{
        @Offset(0) public ForeignRow ModsKey; //foreignrow
    }
    public static class AfflictionFixedMods implements DatType{
        @Offset(0) public int Rarity; //i32
        @Offset(4) public ForeignRow Mod; //foreignrow
    }
    public static class AfflictionRandomModCategories implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class AfflictionRewardMapMods implements DatType{
        @Offset(0) public ForeignRow ModsKey; //foreignrow
    }
    public static class AfflictionRewardTypeVisuals implements DatType{
        @Offset(0) public int AfflictionRewardTypes; //enumrow
        @Offset(4) public String Id; //string
        @Offset(12) public String Name; //string
    }
    public static class AfflictionSplitDemons implements DatType{
        @Offset(4) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(20) public ForeignRow AfflictionRandomModCategoriesKey; //foreignrow
    }
    public static class AfflictionStartDialogue implements DatType{
        @Offset(0) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(16) public ForeignRow NPCTextAudioKey; //foreignrow
    }
    public static class HarvestCraftOptionIcons implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String DDSFile; //string
    }
    public static class HarvestCraftOptions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(16) public ForeignRow Tier; //foreignrow
        @Offset(32) public String Command; //string
        @Offset(40) public String Parameters; //string
        @Offset(64) public short HASH16; //i16
        @Offset(66) public String Description; //string
        @Offset(74) public boolean IsEnchant; //bool
        @Offset(75) public int LifeforceType; //i32
        @Offset(79) public int LifeforceCost; //i32
        @Offset(83) public int SacredCost; //i32
        @Offset(88) public List<ForeignRow> Achievements; //foreignrow
    }
    public static class HarvestCraftTiers implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String FrameImage; //string
        @Offset(16) public String FrameHighlight; //string
    }
    public static class HarvestCraftFilters implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BaseItem; //foreignrow
        @Offset(24) public String Name; //string
    }
    public static class HarvestInfrastructure implements DatType{
        @Offset(0) public String Object; //string
        @Offset(12) public ForeignRow ClientStringsKey; //foreignrow
    }
    public static class HarvestPerLevelValues implements DatType{
        @Offset(0) public int Level; //i32
    }
    public static class HarvestLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int Life; //i32
    }
    public static class HarvestSeeds implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Monster; //foreignrow
        @Offset(24) public int Tier; //i32
        @Offset(48) public List<ForeignRow> Achievements; //foreignrow
        @Offset(64) public String SeedAnimation; //string
        @Offset(76) public String HatchAnimation; //string
        @Offset(84) public int SeedType; //i32
    }
    public static class HarvestSeedItems implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public ForeignRow BaseItem; //foreignrow
        @Offset(20) public ForeignRow DropStat; //foreignrow
    }
    public static class HeistAreaFormationLayout implements DatType{
        @Offset(0) public ForeignRow HeistAreasKey; //foreignrow
    }
    public static class HeistAreas implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> WorldAreasKeys; //foreignrow
        @Offset(28) public ForeignRow EnvironmentsKey1; //foreignrow
        @Offset(44) public ForeignRow EnvironmentsKey2; //foreignrow
        @Offset(60) public List<ForeignRow> HeistJobsKeys; //foreignrow
        @Offset(76) public ForeignRow Contract_BaseItemTypesKey; //foreignrow
        @Offset(92) public ForeignRow Blueprint_BaseItemTypesKey; //foreignrow
        @Offset(108) public String DGRFile; //string
        @Offset(126) public String Blueprint_DDSFile; //string
        @Offset(134) public List<ForeignRow> Achievements1; //foreignrow
        @Offset(150) public List<ForeignRow> Achievements2; //foreignrow
        @Offset(166) public ForeignRow Reward; //foreignrow
        @Offset(182) public List<ForeignRow> Achievements3; //foreignrow
        @Offset(198) public ForeignRow RewardHardmode; //foreignrow
    }
    public static class HeistBalancePerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(28) public ForeignRow HeistValueScalingKey1; //foreignrow
        @Offset(44) public ForeignRow HeistValueScalingKey2; //foreignrow
        @Offset(60) public ForeignRow HeistValueScalingKey3; //foreignrow
        @Offset(76) public ForeignRow HeistValueScalingKey4; //foreignrow
        @Offset(92) public ForeignRow HeistValueScalingKey5; //foreignrow
        @Offset(124) public ForeignRow HeistValueScalingKey6; //foreignrow
        @Offset(140) public ForeignRow HeistValueScalingKey7; //foreignrow
    }
    public static class HeistChestRewardTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Art; //string
        @Offset(16) public String RewardTypeName; //string
        @Offset(32) public String RewardRoomName; //string
        @Offset(40) public int MinLevel; //i32
        @Offset(44) public int MaxLevel; //i32
        @Offset(48) public int Weight; //i32
        @Offset(52) public String RewardRoomName2; //string
        @Offset(60) public List<ForeignRow> HeistJobsKey; //foreignrow
    }
    public static class HeistChests implements DatType{
        @Offset(0) public ForeignRow ChestsKey; //foreignrow
        @Offset(16) public int Weight; //i32
        @Offset(20) public List<ForeignRow> HeistAreasKey; //foreignrow
        @Offset(36) public int HeistChestTypesKey; //enumrow
    }
    public static class HeistChokepointFormation implements DatType{
    }
    public static class HeistConstants implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public float Value; //f32
    }
    public static class HeistContracts implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public ForeignRow HeistAreasKey; //foreignrow
    }
    public static class HeistDoodadNPCs implements DatType{
        @Offset(0) public ForeignRow NPCsKey; //foreignrow
        @Offset(60) public String AOFile; //string
        @Offset(68) public String Stance; //string
        @Offset(76) public ForeignRow BetrayalTargetsKey; //foreignrow
    }
    public static class HeistDoors implements DatType{
        @Offset(0) public String Id; //string
        @Offset(16) public ForeignRow HeistJobsKey1; //foreignrow
        @Offset(32) public ForeignRow HeistJobsKey2; //foreignrow
        @Offset(92) public ForeignRow HeistAreasKey; //foreignrow
    }
    public static class HeistEquipment implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public ForeignRow RequiredJob_HeistJobsKey; //foreignrow
        @Offset(32) public int RequiredLevel; //i32
    }
    public static class HeistGeneration implements DatType{
        @Offset(0) public int Level; //i32
    }
    public static class HeistIntroAreas implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow HeistAreasKey; //foreignrow
        @Offset(32) public String DGRFile; //string
    }
    public static class HeistJobs implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public String RequiredSkillIcon; //string
        @Offset(24) public String SkillIcon; //string
        @Offset(40) public String MapIcon; //string
        @Offset(48) public ForeignRow Level_StatsKey; //foreignrow
        @Offset(64) public ForeignRow Alert_StatsKey; //foreignrow
        @Offset(80) public ForeignRow Alarm_StatsKey; //foreignrow
        @Offset(96) public ForeignRow Cost_StatsKey; //foreignrow
        @Offset(112) public ForeignRow ExperienceGain_StatsKey; //foreignrow
        @Offset(128) public String ConsoleBlueprintLegend; //string
        @Offset(136) public String Description; //string
    }
    public static class HeistJobsExperiencePerLevel implements DatType{
        @Offset(0) public ForeignRow HeistJobsKey; //foreignrow
        @Offset(16) public int Tier; //i32
        @Offset(20) public int Experience; //i32
        @Offset(24) public int MinLevel; //i32
        @Offset(28) public List<ForeignRow> AchievementItemsKey; //foreignrow
    }
    public static class HeistLockType implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow HeistJobsKey; //foreignrow
        @Offset(24) public String SkillIcon; //string
    }
    public static class HeistNPCAuras implements DatType{
        @Offset(0) public ForeignRow Stat; //foreignrow
        @Offset(16) public ForeignRow GrantedEffect; //foreignrow
    }
    public static class HeistNPCBlueprintTypes implements DatType{
        @Offset(0) public ForeignRow NPCsKey; //foreignrow
    }
    public static class HeistNPCDialogue implements DatType{
        @Offset(0) public ForeignRow DialogueEventKey; //foreignrow
        @Offset(16) public ForeignRow HeistNPCsKey; //foreignrow
        @Offset(32) public List<ForeignRow> AudioNormal; //foreignrow
        @Offset(48) public List<ForeignRow> AudioLoud; //foreignrow
    }
    public static class HeistNPCs implements DatType{
        @Offset(0) public ForeignRow NPCsKey; //foreignrow
        @Offset(16) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(32) public List<ForeignRow> SkillLevel_HeistJobsKeys; //foreignrow
        @Offset(48) public String PortraitFile; //string
        @Offset(56) public List<ForeignRow> HeistNPCStatsKeys; //foreignrow
        @Offset(72) public List<Float> StatValues; //f32
        @Offset(92) public List<Integer> SkillLevel_Values; //i32
        @Offset(108) public String Name; //string
        @Offset(116) public String SilhouetteFile; //string
        @Offset(132) public long HeistNPCsKey; //row
        @Offset(140) public List<Float> StatValues2; //f32
        @Offset(156) public ForeignRow Ally_NPCsKey; //foreignrow
        @Offset(172) public String ActiveNPCIcon; //string
        @Offset(180) public ForeignRow HeistJobsKey; //foreignrow
        @Offset(196) public List<ForeignRow> Equip_AchievementItemsKeys; //foreignrow
        @Offset(212) public String AOFile; //string
    }
    public static class HeistNPCStats implements DatType{
        @Offset(0) public ForeignRow StatsKey; //foreignrow
    }
    public static class HeistObjectives implements DatType{
        @Offset(0) public ForeignRow BaseItemType; //foreignrow
        @Offset(16) public float Scaling; //f32
        @Offset(20) public String Name; //string
    }
    public static class HeistObjectiveValueDescriptions implements DatType{
        @Offset(0) public int Tier; //i32
        @Offset(4) public float MarkersMultiply; //f32
        @Offset(8) public String Description; //string
    }
    public static class HeistPatrolPacks implements DatType{
        @Offset(0) public ForeignRow MonsterPacksKey; //foreignrow
    }
    public static class HeistQuestContracts implements DatType{
        @Offset(0) public ForeignRow HeistContractsKey; //foreignrow
        @Offset(16) public ForeignRow HeistObjectivesKey; //foreignrow
        @Offset(32) public List<ForeignRow> HeistNPCsKey; //foreignrow
        @Offset(48) public ForeignRow HeistJobsKey; //foreignrow
        @Offset(77) public ForeignRow HeistRoomsKey1; //foreignrow
        @Offset(93) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(121) public ForeignRow HaveObjective; //foreignrow
        @Offset(138) public ForeignRow QuestActive; //foreignrow
        @Offset(154) public ForeignRow HaveQuest; //foreignrow
        @Offset(170) public ForeignRow HaveObjective2; //foreignrow
        @Offset(188) public String Objective; //string
        @Offset(197) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(214) public ForeignRow HeistIntroAreasKey; //foreignrow
        @Offset(234) public ForeignRow HeistRoomsKey2; //foreignrow
    }
    public static class HeistRevealingNPCs implements DatType{
        @Offset(0) public ForeignRow NPCsKey; //foreignrow
        @Offset(16) public String PortraitFile; //string
        @Offset(24) public List<ForeignRow> NPCAudioKey; //foreignrow
    }
    public static class HeistRooms implements DatType{
        @Offset(0) public ForeignRow HeistAreasKey; //foreignrow
        @Offset(16) public int Id; //i32
        @Offset(20) public String ARMFile; //string
        @Offset(28) public ForeignRow HeistJobsKey1; //foreignrow
        @Offset(44) public ForeignRow HeistJobsKey2; //foreignrow
    }
    public static class HeistValueScaling implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class InfluenceModUpgrades implements DatType{
        @Offset(0) public ForeignRow InfluenceMod; //foreignrow
        @Offset(16) public ForeignRow UpgradedMod; //foreignrow
        @Offset(32) public boolean HighestTier; //bool
    }
    public static class MavenDialog implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow TextAudioT1; //foreignrow
        @Offset(24) public ForeignRow TextAudioT2; //foreignrow
        @Offset(40) public ForeignRow TextAudioT3; //foreignrow
        @Offset(56) public ForeignRow TextAudioT4; //foreignrow
        @Offset(72) public ForeignRow TextAudioT5; //foreignrow
        @Offset(89) public ForeignRow TextAudioT6; //foreignrow
    }
    public static class MavenFights implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int WitnessesRequired; //i32
        @Offset(12) public int AreaLevel; //i32
        @Offset(24) public ForeignRow BaseItemType; //foreignrow
        @Offset(44) public int MinMapTier; //i32
        @Offset(68) public List<ForeignRow> WitnessAreas; //foreignrow
        @Offset(117) public List<ForeignRow> Achievements1; //foreignrow
        @Offset(133) public List<ForeignRow> Achievements2; //foreignrow
    }
    public static class MavenJewelRadiusKeystones implements DatType{
        @Offset(0) public ForeignRow Keystone; //foreignrow
    }
    public static class RitualBalancePerLevel implements DatType{
        @Offset(0) public int MinLevel; //i32
    }
    public static class RitualConstants implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public float Value; //f32
    }
    public static class RitualRuneTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MiscAnimatedKey1; //foreignrow
        @Offset(24) public int SpawnWeight; //i32
        @Offset(28) public int LevelMin; //i32
        @Offset(32) public int LevelMax; //i32
        @Offset(36) public ForeignRow BuffDefinitionsKey; //foreignrow
        @Offset(52) public List<Integer> BuffStatValues; //i32
        @Offset(68) public List<ForeignRow> SpawnPatterns; //foreignrow
        @Offset(84) public List<ForeignRow> ModsKey; //foreignrow
        @Offset(132) public ForeignRow MiscAnimatedKey2; //foreignrow
        @Offset(148) public ForeignRow EnvironmentsKey; //foreignrow
        @Offset(168) public List<ForeignRow> Achievements; //foreignrow
        @Offset(184) public String Type; //string
        @Offset(192) public String Description; //string
        @Offset(200) public ForeignRow DaemonSpawningDataKey; //foreignrow
    }
    public static class RitualSetKillAchievements implements DatType{
        @Offset(0) public ForeignRow Achievement; //foreignrow
        @Offset(16) public List<ForeignRow> KillBosses; //foreignrow
    }
    public static class RitualSpawnPatterns implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public List<String> SpawnOrder; //string
    }
    public static class UltimatumEncounters implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
        @Offset(16) public List<ForeignRow> ModTypes; //foreignrow
        @Offset(32) public String BossARMFile; //string
        @Offset(40) public ForeignRow Type; //foreignrow
        @Offset(56) public String Icon; //string
        @Offset(64) public int HASH16; //i32
    }
    public static class UltimatumEncounterTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public String ProgressBarText; //string
        @Offset(26) public List<ForeignRow> NormalAchievements; //foreignrow
        @Offset(42) public ForeignRow InscribedAchievement; //foreignrow
    }
    public static class UltimatumItemisedRewards implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int HASH16; //i32
        @Offset(12) public String RewardText; //string
        @Offset(20) public ForeignRow ItemVisualIdentityKey; //foreignrow
        @Offset(36) public int RewardType; //i32
        @Offset(40) public ForeignRow SacrificeItem; //foreignrow
        @Offset(56) public int SacrificeAmount; //i32
        @Offset(60) public String SacrificeText; //string
        @Offset(69) public List<ForeignRow> TrialMods; //foreignrow
    }
    public static class UltimatumMapModifiers implements DatType{
        @Offset(0) public ForeignRow Stat; //foreignrow
        @Offset(16) public List<ForeignRow> Mods; //foreignrow
    }
    public static class UltimatumModifiers implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> Types; //foreignrow
        @Offset(24) public List<ForeignRow> ExtraMods; //foreignrow
        @Offset(40) public List<ForeignRow> TypesFiltered; //foreignrow
        @Offset(56) public ForeignRow DaemonSpawningData; //foreignrow
        @Offset(72) public long PreviousTier; //row
        @Offset(81) public int Radius; //i32
        @Offset(85) public String Name; //string
        @Offset(93) public String Icon; //string
        @Offset(101) public int HASH16; //i32
        @Offset(105) public List<ForeignRow> TypesExtra; //foreignrow
        @Offset(121) public int MonsterTypesApplyingRuin; //i32
        @Offset(125) public ForeignRow MiscAnimated; //foreignrow
        @Offset(141) public List<ForeignRow> BuffTemplates; //foreignrow
        @Offset(157) public int Tier; //i32
        @Offset(165) public String Description; //string
        @Offset(173) public List<String> MonsterSpawners; //string
        @Offset(190) public List<ForeignRow> Achievements; //foreignrow
        @Offset(206) public ForeignRow TextAudio; //foreignrow
        @Offset(222) public ForeignRow UniqueMapMod; //foreignrow
    }
    public static class UltimatumModifierTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class UltimatumTrialMasterAudio implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int Variant; //i32
        @Offset(20) public ForeignRow TextAudio; //foreignrow
        @Offset(36) public int RoundsMin; //i32
        @Offset(40) public int RoundsMax; //i32
    }
    public static class ExpeditionAreas implements DatType{
        @Offset(0) public ForeignRow Area; //foreignrow
        @Offset(16) public int PosX; //i32
        @Offset(20) public int PosY; //i32
        @Offset(24) public List<ForeignRow> Tags; //foreignrow
        @Offset(57) public ForeignRow TextAudio; //foreignrow
        @Offset(73) public List<ForeignRow> CompletionAchievements; //foreignrow
    }
    public static class ExpeditionBalancePerLevel implements DatType{
        @Offset(0) public int Level; //i32
    }
    public static class ExpeditionCurrency implements DatType{
        @Offset(0) public ForeignRow BaseItemType; //foreignrow
    }
    public static class ExpeditionDeals implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public String Function; //string
        @Offset(12) public String Arguments; //string
        @Offset(20) public ForeignRow TextAudio; //foreignrow
        @Offset(36) public String Description; //string
        @Offset(44) public List<ForeignRow> BuyAchievements; //foreignrow
        @Offset(76) public int DealFamily; //enumrow
    }
    public static class ExpeditionFactions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public String FactionFlag; //string
        @Offset(28) public String FactionIcon; //string
        @Offset(36) public ForeignRow MonsterVarieties; //foreignrow
        @Offset(52) public ForeignRow Progress1; //foreignrow
        @Offset(68) public ForeignRow Progress2Vaal; //foreignrow
        @Offset(84) public ForeignRow Progress3Final; //foreignrow
        @Offset(100) public ForeignRow Tags; //foreignrow
    }
    public static class ExpeditionMarkersCommon implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String AOFile; //string
    }
    public static class ExpeditionNPCs implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> NPCs; //foreignrow
        @Offset(24) public ForeignRow RerollItem; //foreignrow
        @Offset(64) public ForeignRow Faction; //foreignrow
        @Offset(80) public ForeignRow Reroll; //foreignrow
        @Offset(96) public ForeignRow AllBombsPlaced; //foreignrow
        @Offset(112) public ForeignRow BombPlacedRemnant; //foreignrow
        @Offset(128) public ForeignRow BombPlacedTreasure; //foreignrow
        @Offset(144) public ForeignRow BombPlacedMonsters; //foreignrow
        @Offset(160) public ForeignRow BombPlacedGeneric; //foreignrow
        @Offset(176) public ForeignRow EncounterComplete; //foreignrow
    }
    public static class ExpeditionRelicMods implements DatType{
        @Offset(0) public ForeignRow Mod; //foreignrow
        @Offset(16) public List<Integer> Categories; //enumrow
        @Offset(32) public List<ForeignRow> DestroyAchievements; //foreignrow
    }
    public static class ExpeditionRelics implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public ForeignRow ItemTag; //foreignrow
        @Offset(32) public String AOFile; //string
        @Offset(40) public int MinLevel; //i32
        @Offset(44) public int MaxLevel; //i32
    }
    public static class ExpeditionStorageLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow StoredItem; //foreignrow
        @Offset(24) public int XOffset; //i32
        @Offset(28) public int YOffset; //i32
        @Offset(40) public int Width; //i32
        @Offset(44) public int Height; //i32
    }
    public static class ExpeditionTerrainFeatures implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow ExtraFeature; //foreignrow
        @Offset(24) public ForeignRow ExpeditionFaction; //foreignrow
        @Offset(40) public int MinLevel; //i32
        @Offset(44) public int MaxLevel; //i32
        @Offset(52) public ForeignRow Area; //foreignrow
        @Offset(68) public List<ForeignRow> ExpeditionAreas; //foreignrow
        @Offset(89) public List<ForeignRow> UnearthAchievements; //foreignrow
    }
    public static class HellscapeAOReplacements implements DatType{
        @Offset(0) public String Original; //string
        @Offset(8) public int HASH32; //i32
        @Offset(12) public String Replacement; //string
    }
    public static class HellscapeAreaPacks implements DatType{
        @Offset(0) public ForeignRow WorldArea; //foreignrow
        @Offset(16) public List<ForeignRow> MonsterPacks; //foreignrow
    }
    public static class HellscapeExperienceLevels implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int Experience; //i32
    }
    public static class HellscapeFactions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(84) public ForeignRow Boss; //foreignrow
    }
    public static class HellscapeImmuneMonsters implements DatType{
        @Offset(0) public ForeignRow Monster; //foreignrow
    }
    public static class HellscapeItemModificationTiers implements DatType{
        @Offset(0) public int Tier; //i32
        @Offset(4) public boolean IsMap; //bool
        @Offset(9) public int MinItemLvl; //i32
    }
    public static class HellscapeLifeScalingPerLevel implements DatType{
        @Offset(0) public int AreaLevel; //i32
        @Offset(4) public int Scale; //i32
    }
    public static class HellscapeModificationInventoryLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int Column; //i32
        @Offset(12) public int Row; //i32
        @Offset(16) public boolean IsMapSlot; //bool
        @Offset(21) public int Width; //i32
        @Offset(25) public int Height; //i32
        @Offset(29) public ForeignRow Stat; //foreignrow
        @Offset(45) public int StatValue; //i32
        @Offset(49) public ForeignRow UnlockedWith; //foreignrow
        @Offset(65) public ForeignRow Quest; //foreignrow
    }
    public static class HellscapeMods implements DatType{
        @Offset(0) public ForeignRow Mod; //foreignrow
        @Offset(16) public List<Integer> TiersWhitelist; //i32
        @Offset(32) public ForeignRow TransformAchievement; //foreignrow
        @Offset(48) public List<ForeignRow> ModFamilies; //foreignrow
    }
    public static class HellscapeMonsterPacks implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MonsterPack; //foreignrow
        @Offset(24) public ForeignRow Faction; //foreignrow
        @Offset(40) public boolean IsDonutPack; //bool
        @Offset(41) public boolean IsElite; //bool
        @Offset(42) public int MinLevel; //i32
        @Offset(46) public int MaxLevel; //i32
    }
    public static class HellscapePassives implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public List<ForeignRow> Stats; //foreignrow
        @Offset(32) public List<Integer> StatsValues; //i32
        @Offset(48) public int Points; //i32
        @Offset(52) public int HASH16; //i32
        @Offset(56) public String Icon; //string
        @Offset(64) public String IconMaxed; //string
        @Offset(72) public ForeignRow SoundEffect; //foreignrow
        @Offset(92) public ForeignRow Quest; //foreignrow
    }
    public static class HellscapePassiveTree implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int AllocationsRequired; //i32
        @Offset(12) public List<ForeignRow> Passives; //foreignrow
    }
    public static class ArchnemesisMetaRewards implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String RewardText; //string
        @Offset(16) public int RewardGroup; //i32
        @Offset(20) public String ScriptArgument; //string
        @Offset(28) public int MinLevel; //i32
        @Offset(32) public int MaxLevel; //i32
    }
    public static class ArchnemesisModComboAchievements implements DatType{
        @Offset(0) public ForeignRow Achievement; //foreignrow
        @Offset(16) public List<ForeignRow> Mods; //foreignrow
    }
    public static class ArchnemesisMods implements DatType{
        @Offset(0) public ForeignRow Mod; //foreignrow
        @Offset(16) public String Name; //string
        @Offset(24) public ForeignRow Visual; //foreignrow
        @Offset(40) public List<String> TextStyles; //string
    }
    public static class ArchnemesisModVisuals implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class ArchnemesisRecipes implements DatType{
        @Offset(0) public ForeignRow Result; //foreignrow
        @Offset(16) public List<ForeignRow> Recipe; //foreignrow
    }
    public static class AtlasPrimordialAltarChoices implements DatType{
        @Offset(0) public ForeignRow Mod; //foreignrow
        @Offset(16) public ForeignRow Type; //foreignrow
    }
    public static class AtlasPrimordialAltarChoiceTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String TopIconEater; //string
        @Offset(16) public String BottomIconEater; //string
        @Offset(24) public String TopIconExarch; //string
        @Offset(32) public String BottomIconExarch; //string
        @Offset(40) public String Text; //string
    }
    public static class AtlasPrimordialBosses implements DatType{
        @Offset(0) public String Id; //string
        @Offset(24) public ForeignRow InfluenceComplete; //foreignrow
        @Offset(40) public ForeignRow MiniBossInvitation; //foreignrow
        @Offset(56) public ForeignRow BossInvitation; //foreignrow
        @Offset(72) public ForeignRow PickUpKey; //foreignrow
        @Offset(120) public ForeignRow Tag; //foreignrow
        @Offset(136) public ForeignRow Altar; //foreignrow
        @Offset(152) public ForeignRow AltarActivated; //foreignrow
    }
    public static class AtlasPrimordialBossInfluence implements DatType{
        @Offset(0) public ForeignRow Boss; //foreignrow
        @Offset(16) public int Progress; //i32
        @Offset(20) public int MinMapTier; //i32
    }
    public static class AtlasPrimordialBossOptions implements DatType{
        @Offset(8) public String DefaultIcon; //string
        @Offset(16) public String HoverIcon; //string
        @Offset(24) public String HighlightIcon; //string
        @Offset(32) public String EmptyIcon; //string
        @Offset(40) public ForeignRow Description; //foreignrow
        @Offset(56) public ForeignRow DescriptionActive; //foreignrow
        @Offset(72) public String ProgressTracker; //string
        @Offset(80) public String ProgressTrackerFill; //string
        @Offset(88) public String Name; //string
        @Offset(96) public String MapDeviceTrackerFill; //string
    }
    public static class PrimordialBossLifeScalingPerLevel implements DatType{
        @Offset(0) public int AreaLevel; //i32
        @Offset(4) public int Scale; //i32
    }
    public static class AtlasUpgradesInventoryLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public ForeignRow Voidstone; //foreignrow
        @Offset(32) public String Objective; //string
        @Offset(40) public ForeignRow GrantAtlasUpgrade; //foreignrow
    }
    public static class AtlasPassiveSkillTreeGroupType implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class KiracLevels implements DatType{
        @Offset(0) public int AreaLevel; //i32
    }
    public static class ScoutingReports implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BaseItemType; //foreignrow
        @Offset(24) public int MinMapTier; //i32
    }
    public static class DroneBaseTypes implements DatType{
        @Offset(0) public ForeignRow BaseType; //foreignrow
        @Offset(16) public ForeignRow Type; //foreignrow
        @Offset(32) public int Charges; //i32
        @Offset(36) public int Duration; //i32
        @Offset(40) public int EnemyLimit; //i32
        @Offset(44) public ForeignRow Visual; //foreignrow
        @Offset(60) public int Empowerment; //i32
        @Offset(64) public ForeignRow UseAchievement; //foreignrow
        @Offset(80) public boolean CreatedViaPowerCore; //bool
    }
    public static class DroneTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(40) public String DeployText; //string
        @Offset(56) public ForeignRow UnlockedStat; //foreignrow
        @Offset(72) public String SocketableText; //string
        @Offset(80) public String NotPoweredText; //string
    }
    public static class SentinelCraftingCurrency implements DatType{
        @Offset(0) public ForeignRow Currency; //foreignrow
        @Offset(16) public int Type; //i32
    }
    public static class SentinelDroneInventoryLayout implements DatType{
        @Offset(0) public ForeignRow DroneType; //foreignrow
    }
    public static class SentinelPassives implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int HASH16; //i32
    }
    public static class SentinelPassiveStats implements DatType{
    }
    public static class SentinelPassiveTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String DefaultIcon; //string
        @Offset(16) public String ActiveIcon; //string
        @Offset(24) public ForeignRow DroneType; //foreignrow
    }
    public static class SentinelPowerExpLevels implements DatType{
    }
    public static class SentinelStorageLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow StoredItem; //foreignrow
        @Offset(24) public ForeignRow DroneType; //foreignrow
        @Offset(41) public String TabIcon; //string
        @Offset(49) public int XOffset; //i32
        @Offset(53) public int YOffset; //i32
        @Offset(65) public int Width; //i32
        @Offset(69) public int Height; //i32
        @Offset(73) public int SlotSize; //i32
    }
    public static class SentinelTaggedMonsterStats implements DatType{
        @Offset(0) public ForeignRow TaggedStat; //foreignrow
    }
    public static class ClientLakeDifficulty implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public float Scaling; //f32
    }
    public static class LakeBossLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int Scaling; //i32
    }
    public static class LakeMetaOptions implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class LakeMetaOptionsUnlockText implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public String Text; //string
    }
    public static class LakeRoomCompletion implements DatType{
        @Offset(0) public ForeignRow Room; //foreignrow
        @Offset(24) public List<ForeignRow> Achievements; //foreignrow
    }
    public static class LakeRooms implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<String> ARMFiles; //string
        @Offset(24) public String Script; //string
        @Offset(48) public List<ForeignRow> Stats; //foreignrow
        @Offset(64) public List<Integer> StatsValues; //i32
        @Offset(80) public String Description; //string
        @Offset(88) public String Name; //string
        @Offset(96) public int Type; //i32
        @Offset(100) public ForeignRow WorldArea; //foreignrow
        @Offset(116) public String Icon; //string
        @Offset(128) public int MinLevel; //i32
        @Offset(149) public List<ForeignRow> ExtraStats; //foreignrow
        @Offset(165) public List<Integer> ExtraStatsValues; //i32
        @Offset(181) public String ReminderText; //string
        @Offset(189) public ForeignRow TextAudio; //foreignrow
    }
    public static class StampChoice implements DatType{
        @Offset(0) public String Id; //string
        @Offset(28) public String Effect; //string
        @Offset(36) public String Icon; //string
    }
    public static class StampFamily implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class SanctumAirlocks implements DatType{
        @Offset(0) public ForeignRow Floor; //foreignrow
        @Offset(36) public ForeignRow Area1; //foreignrow
        @Offset(52) public ForeignRow Area2; //foreignrow
    }
    public static class SanctumDefenceIcons implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Stat; //foreignrow
        @Offset(24) public String DefenceIcon; //string
        @Offset(32) public String DefenceBrokenIcon; //string
        @Offset(40) public ForeignRow BrokenStat; //foreignrow
        @Offset(56) public String Description; //string
    }
    public static class SanctumDeferredRewardCategories implements DatType{
        @Offset(0) public ForeignRow Item; //foreignrow
        @Offset(16) public String NamePlural; //string
        @Offset(24) public String ScriptReward; //string
    }
    public static class SanctumDeferredRewards implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String DeferralIcon; //string
        @Offset(16) public ForeignRow DeferralCategory; //foreignrow
        @Offset(32) public ForeignRow RewardCategory; //foreignrow
        @Offset(48) public int Count; //i32
    }
    public static class SanctumFloors implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Area; //foreignrow
        @Offset(24) public ForeignRow Title; //foreignrow
        @Offset(40) public String RoomIcon; //string
        @Offset(48) public String BossIcon; //string
        @Offset(56) public String Description; //string
        @Offset(64) public ForeignRow Summary; //foreignrow
        @Offset(80) public ForeignRow Itemised; //foreignrow
    }
    public static class SanctumFodderLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
    }
    public static class SanctumLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
    }
    public static class SanctumPersistentEffectCategories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Frame; //string
        @Offset(16) public String Popup; //string
        @Offset(24) public String Glow; //string
        @Offset(32) public boolean Curse; //bool
        @Offset(33) public boolean Boon; //bool
        @Offset(34) public String Icon; //string
        @Offset(42) public String Name; //string
        @Offset(50) public boolean Deferral; //bool
    }
    public static class SanctumPersistentEffects implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> Stats; //foreignrow
        @Offset(24) public List<Integer> StatValues; //i32
        @Offset(40) public String Name; //string
        @Offset(48) public String Icon; //string
        @Offset(61) public ForeignRow EffectCategory; //foreignrow
        @Offset(77) public long NextEffect; //row
        @Offset(93) public String BoonDesc; //string
        @Offset(101) public String CurseDesc; //string
        @Offset(134) public List<ForeignRow> Guard; //foreignrow
        @Offset(150) public long FirstEffect; //row
        @Offset(168) public int HASH16; //i32
    }
    public static class SanctumRooms implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String ArmFile; //string
        @Offset(16) public ForeignRow RoomType; //foreignrow
        @Offset(32) public String Script; //string
        @Offset(40) public ForeignRow Floor; //foreignrow
        @Offset(56) public ForeignRow Area; //foreignrow
    }
    public static class SanctumRoomTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(43) public String Icon; //string
        @Offset(52) public String Description; //string
        @Offset(76) public List<ForeignRow> Rooms; //foreignrow
    }
    public static class SanctumSelectionDisplayOverride implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Reward; //string
        @Offset(16) public String Icon; //string
        @Offset(24) public String Downside; //string
        @Offset(32) public ForeignRow Item; //foreignrow
        @Offset(48) public int Count; //i32
        @Offset(52) public ForeignRow RewardCategory; //foreignrow
    }
    public static class WeaponPassiveSkillTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class WeaponPassiveTreeBalancePerItemLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int Bar1; //i32
        @Offset(8) public int Bar2; //i32
        @Offset(12) public int Bar3; //i32
        @Offset(16) public int Bar4; //i32
        @Offset(20) public int Bar5; //i32
    }
    public static class WeaponPassiveTreeUniqueBaseTypes implements DatType{
        @Offset(0) public ForeignRow UniqueBase; //foreignrow
    }
    public static class WeaponPassiveSkills implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int Tier; //i32
        @Offset(12) public ForeignRow Mod; //foreignrow
        @Offset(60) public String Icon; //string
        @Offset(68) public ForeignRow Type; //foreignrow
        @Offset(84) public List<ForeignRow> Achievements; //foreignrow
    }
    public static class CrucibleDifficulty implements DatType{
        @Offset(0) public String Number; //string
        @Offset(8) public String Name; //string
    }
    public static class CrucibleEndgameMonsterPacks implements DatType{
        @Offset(0) public String Tier; //string
    }
    public static class CrucibleLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int Life; //i32
    }
    public static class CruciblePlayerClassOffsets implements DatType{
    }
    public static class CrucibleTags implements DatType{
        @Offset(0) public String Tag; //string
    }
    public static class AncestralEmbraceVariations implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Minion; //foreignrow
    }
    public static class AncestralLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
    }
    public static class AncestralTrialChieftains implements DatType{
    }
    public static class AncestralTrialDialogue implements DatType{
        @Offset(36) public List<ForeignRow> TextAudio; //foreignrow
    }
    public static class AncestralTrialDialogueEvents implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class AncestralTrialFieldItems implements DatType{
        @Offset(32) public int HASH32; //i32
        @Offset(36) public String Description; //string
        @Offset(44) public List<ForeignRow> Achievement; //foreignrow
    }
    public static class AncestralTrialItems implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int HASH16; //i32
        @Offset(12) public String Name; //string
        @Offset(20) public String Image; //string
        @Offset(48) public List<ForeignRow> Mods; //foreignrow
        @Offset(64) public String FlavorText; //string
    }
    public static class AncestralTrialMonsters implements DatType{
        @Offset(16) public ForeignRow Monster; //foreignrow
        @Offset(52) public List<ForeignRow> Achievement; //foreignrow
    }
    public static class AncestralTrialPositionType implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String PlayerTotemIcon; //string
        @Offset(16) public String Name; //string
        @Offset(24) public String EnemyTotemIcon; //string
        @Offset(32) public String PlayerTotemSmallIcon; //string
        @Offset(40) public String EnemyTotemSmallIcon; //string
    }
    public static class AncestralTrialShopSlotEntries implements DatType{
    }
    public static class AncestralTrialTribeOpinions implements DatType{
        @Offset(0) public ForeignRow SourceTribe; //foreignrow
        @Offset(16) public ForeignRow TargetTribe; //foreignrow
        @Offset(32) public int Opinion; //enumrow
    }
    public static class AncestralTrialTribes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(24) public String Portrait; //string
        @Offset(32) public String TribeIcon; //string
        @Offset(40) public String TribeName; //string
        @Offset(48) public String FavourTracker; //string
        @Offset(72) public String Name; //string
    }
    public static class AncestralTrialUnitPositions implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class AncestralTrialUnits implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public ForeignRow Tribe; //foreignrow
        @Offset(32) public String Image; //string
        @Offset(40) public String TotemImage; //string
        @Offset(48) public int HASH16; //i32
        @Offset(56) public String Description; //string
        @Offset(72) public ForeignRow Position; //foreignrow
    }
    public static class ChieftainLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
    }
    public static class PassiveSkillTattoos implements DatType{
        @Offset(0) public ForeignRow Tattoo; //foreignrow
        @Offset(16) public ForeignRow Override; //foreignrow
        @Offset(32) public ForeignRow Set; //foreignrow
        @Offset(48) public int Tribe; //i32
        @Offset(52) public ForeignRow OverrideType; //foreignrow
    }
    public static class PassiveSkillTattooTargetSets implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<Integer> Set; //i32
        @Offset(24) public String Name; //string
        @Offset(32) public String Qualifier; //string
    }
    public static class AfflictionDustedMonsterCurrencyDropWeightingByItemRarity implements DatType{
    }
    public static class AzmeriCounterQuests implements DatType{
    }
    public static class AzmeriFeatureRooms implements DatType{
    }
    public static class AzmeriLifeScalingPerLevel implements DatType{
    }
    public static class AzmeriWoodsDustType implements DatType{
    }
    public static class CorpseTypeTags implements DatType{
        @Offset(0) public ForeignRow Tag; //foreignrow
        @Offset(16) public String Name; //string
        @Offset(24) public ForeignRow RavenousBuff; //foreignrow
        @Offset(40) public String Icon; //string
    }
    public static class Descendancy implements DatType{
    }
    public static class ItemisedCorpse implements DatType{
        @Offset(0) public ForeignRow BaseItem; //foreignrow
        @Offset(16) public ForeignRow MonsterVariety; //foreignrow
        @Offset(32) public String MonsterAbilities; //string
        @Offset(40) public ForeignRow MonsterCategory; //foreignrow
    }
    public static class Tinctures implements DatType{
        @Offset(0) public ForeignRow BaseItem; //foreignrow
        @Offset(16) public int DebuffInterval; //i32
        @Offset(20) public int Cooldown; //i32
    }
    public static class ItemisedNecropolisPacks implements DatType{
        @Offset(0) public ForeignRow Item; //foreignrow
        @Offset(16) public ForeignRow Pack; //foreignrow
        @Offset(32) public String Description; //string
    }
    public static class NecropolisCraftBases implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BaseItem; //foreignrow
        @Offset(24) public ForeignRow UniqueCraft; //foreignrow
        @Offset(45) public ForeignRow CraftItemType; //foreignrow
        @Offset(61) public String Name; //string
        @Offset(69) public ForeignRow CraftTag; //foreignrow
    }
    public static class NecropolisCraftingMods implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public List<ForeignRow> Stats; //foreignrow
        @Offset(28) public List<Integer> StatsValues; //i32
        @Offset(44) public ForeignRow CraftingItemType; //foreignrow
        @Offset(64) public boolean CorpsesNotConsumed; //bool
        @Offset(65) public List<ForeignRow> CraftingItemTypes; //foreignrow
        @Offset(81) public List<ForeignRow> Achievements; //foreignrow
        @Offset(97) public int MatchingMonsters; //i32
    }
    public static class NecropolisCraftingModsFromStats implements DatType{
        @Offset(0) public ForeignRow Stat; //foreignrow
        @Offset(16) public ForeignRow CraftingMod; //foreignrow
    }
    public static class NecropolisCraftItemTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String ExorciseIcon; //string
        @Offset(16) public String Name; //string
        @Offset(28) public String IllustratuionIcon; //string
        @Offset(36) public ForeignRow TextAudioLong; //foreignrow
        @Offset(52) public ForeignRow Achievements; //foreignrow
        @Offset(68) public ForeignRow TextAudioShort; //foreignrow
    }
    public static class NecropolisPackAdditionalPacks implements DatType{
    }
    public static class NecropolisPackImplicitClass implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MoreImplicit; //foreignrow
        @Offset(24) public ForeignRow LessImplicit; //foreignrow
    }
    public static class NecropolisPackImplicits implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(16) public String Icon; //string
    }
    public static class NecropolisPackMods implements DatType{
        @Offset(0) public ForeignRow Mod; //foreignrow
        @Offset(16) public ForeignRow Tier; //foreignrow
        @Offset(32) public long NextTier; //row
        @Offset(40) public long PrevTier; //row
    }
    public static class NecropolisPackModTiers implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Icon; //string
        @Offset(16) public String Name; //string
        @Offset(24) public List<String> TextColours; //string
        @Offset(40) public boolean SpecialTier; //bool
    }
    public static class NecropolisPacks implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public String Icon; //string
        @Offset(24) public String Description; //string
        @Offset(32) public String PackLeader1; //string
        @Offset(40) public String PackLeader2; //string
        @Offset(48) public ForeignRow Mod; //foreignrow
    }
    public static class NecropolisPacksPerArea implements DatType{
    }
    public static class NecropolisUniqueCrafts implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> Stats; //foreignrow
    }
    public static class PackFrequencyNames implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public float Frequency; //f32
        @Offset(12) public ForeignRow Text; //foreignrow
    }
    public static class CurrencyExchange implements DatType{
        @Offset(0) public ForeignRow Item; //foreignrow
        @Offset(16) public ForeignRow Category; //foreignrow
        @Offset(32) public ForeignRow SubCategory; //foreignrow
        @Offset(49) public boolean EnabledInChallengeLeague; //bool
        @Offset(50) public int GoldPurchaseFee; //i32
    }
    public static class CurrencyExchangeCategories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
    }
    public static class VillageAssignWorkerTextAudio implements DatType{
    }
    public static class VillageExports implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(20) public String Icon; //string
        @Offset(36) public String Icon2; //string
        @Offset(44) public String Name2; //string
        @Offset(52) public String IconCombined; //string
        @Offset(60) public long Result; //row
    }
    public static class VillageFarmAdjacency implements DatType{
    }
    public static class VillageJobs implements DatType{
        @Offset(20) public String Status; //string
        @Offset(28) public ForeignRow Stat; //foreignrow
        @Offset(44) public int StatValue; //i32
    }
    public static class VillageJobTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Icon; //string
        @Offset(16) public String StateText; //string
        @Offset(24) public ForeignRow MaxWorkersStat; //foreignrow
        @Offset(41) public ForeignRow JobSpeedStat; //foreignrow
    }
    public static class VillageResources implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class VillageSharedConstants implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int Value; //i32
    }
    public static class VillageUniqueDisenchantValues implements DatType{
        @Offset(0) public ForeignRow UniqueName; //foreignrow
        @Offset(16) public float Value; //f32
    }
    public static class VillageUpgradeCategories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String ScriptArgs; //string
        @Offset(16) public String Name; //string
        @Offset(24) public String Icon; //string
        @Offset(32) public String Text; //string
    }
    public static class VillageUpgrades implements DatType{
        @Offset(16) public int Tier; //i32
        @Offset(20) public String Text; //string
        @Offset(68) public List<ForeignRow> RuneItem; //foreignrow
        @Offset(100) public List<ForeignRow> Stats; //foreignrow
        @Offset(116) public List<Integer> StatsValues; //i32
        @Offset(132) public ForeignRow UpgradeTextAudio; //foreignrow
    }
    public static class AtlasTrees implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(24) public String UnlockObjective; //string
    }
    public static class AchievementItemRewards implements DatType{
        @Offset(0) public ForeignRow AchievementItemsKey; //foreignrow
        @Offset(16) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(32) public String Message; //string
        @Offset(40) public String Id; //string
    }
    public static class AchievementItems implements DatType{
        @Offset(0) public String Id; //string
        @Offset(16) public String Name; //string
        @Offset(24) public int CompletionsRequired; //i32
        @Offset(28) public ForeignRow AchievementsKey; //foreignrow
    }
    public static class Achievements implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
        @Offset(16) public int SetId; //i32
        @Offset(20) public String Objective; //string
        @Offset(28) public short HASH16; //i16
        @Offset(31) public boolean HideAchievementItems; //bool
        @Offset(33) public int MinCompletedItems; //i32
        @Offset(37) public boolean TwoColumnLayout; //bool
        @Offset(38) public boolean ShowItemCompletionsAsOne; //bool
        @Offset(47) public boolean SoftcoreOnly; //bool
        @Offset(48) public boolean HardcoreOnly; //bool
    }
    public static class AchievementSetRewards implements DatType{
        @Offset(0) public int SetId; //i32
        @Offset(4) public int AchievementsRequired; //i32
        @Offset(8) public List<ForeignRow> Rewards; //foreignrow
        @Offset(24) public int TotemPieceEveryNAchievements; //i32
        @Offset(28) public String Message; //string
        @Offset(36) public String NotificationIcon; //string
        @Offset(44) public String HideoutName; //string
        @Offset(52) public String Id; //string
    }
    public static class AchievementSetsDisplay implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public String Title; //string
    }
    public static class ActiveSkills implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String DisplayedName; //string
        @Offset(16) public String Description; //string
        @Offset(24) public ForeignRow ActionType; //foreignrow
        @Offset(40) public String Icon_DDSFile; //string
        @Offset(48) public List<Integer> ActiveSkillTargetTypes; //enumrow
        @Offset(64) public List<ForeignRow> ActiveSkillTypes; //foreignrow
        @Offset(80) public List<ForeignRow> WeaponRestriction_ItemClassesKeys; //foreignrow
        @Offset(96) public String WebsiteDescription; //string
        @Offset(104) public String WebsiteImage; //string
        @Offset(122) public int SkillTotemId; //enumrow
        @Offset(126) public boolean IsManuallyCasted; //bool
        @Offset(127) public List<ForeignRow> Input_StatKeys; //foreignrow
        @Offset(143) public List<ForeignRow> Output_StatKeys; //foreignrow
        @Offset(159) public List<ForeignRow> MinionActiveSkillTypes; //foreignrow
        @Offset(197) public ForeignRow AlternateSkillTargetingBehavioursKey; //foreignrow
        @Offset(214) public String AIFile; //string
        @Offset(241) public long TransfigureBase; //row
        @Offset(249) public String VideoClip; //string
        @Offset(273) public String AiScript; //string
        @Offset(326) public String StatDescription; //string
    }
    public static class ActiveSkillType implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow FlagStat; //foreignrow
    }
    public static class Acts implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String UI_Title; //string
        @Offset(16) public int ActNumber; //i32
        @Offset(40) public boolean IsEndGame; //bool
        @Offset(125) public String Description; //string
    }
    public static class AddBuffToTargetVarieties implements DatType{
        @Offset(0) public ForeignRow BuffDefinitions; //foreignrow
        @Offset(32) public List<ForeignRow> StatsKeys; //foreignrow
    }
    public static class AdditionalLifeScaling implements DatType{
        @Offset(0) public int IntId; //i32
        @Offset(4) public String ID; //string
        @Offset(12) public String DatFile; //string
    }
    public static class AdditionalMonsterPacksFromStats implements DatType{
        @Offset(0) public ForeignRow StatsKey; //foreignrow
        @Offset(20) public List<ForeignRow> MonsterPacksKeys; //foreignrow
        @Offset(36) public int AdditionalMonsterPacksStatMode; //i32
        @Offset(40) public ForeignRow PackCountStatsKey; //foreignrow
        @Offset(56) public List<ForeignRow> StatsKeys; //foreignrow
        @Offset(72) public List<Integer> StatsValues; //i32
        @Offset(92) public ForeignRow PackSizeStatsKey; //foreignrow
    }
    public static class AdvancedSkillsTutorial implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> SkillGemInfoKey1; //foreignrow
        @Offset(24) public List<ForeignRow> SkillGemInfoKey2; //foreignrow
        @Offset(40) public String Description; //string
        @Offset(48) public String International_BK2File; //string
        @Offset(56) public ForeignRow SkillGemsKey; //foreignrow
        @Offset(72) public String China_BK2File; //string
        @Offset(80) public List<ForeignRow> CharactersKey; //foreignrow
    }
    public static class AegisVariations implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public boolean DefendsAgainstPhysical; //bool
        @Offset(9) public boolean DefendsAgainstFire; //bool
        @Offset(10) public boolean DefendsAgainstCold; //bool
        @Offset(11) public boolean DefendsAgainstLightning; //bool
        @Offset(12) public boolean DefendsAgainstChaos; //bool
    }
    public static class AlternatePassiveAdditions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow AlternateTreeVersionsKey; //foreignrow
        @Offset(24) public int SpawnWeight; //i32
        @Offset(28) public List<ForeignRow> StatsKeys; //foreignrow
        @Offset(44) public int Stat1Min; //i32
        @Offset(48) public int Stat1Max; //i32
        @Offset(68) public List<Integer> PassiveType; //i32
    }
    public static class AlternatePassiveSkills implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow AlternateTreeVersionsKey; //foreignrow
        @Offset(24) public String Name; //string
        @Offset(32) public List<Integer> PassiveType; //i32
        @Offset(48) public List<ForeignRow> StatsKeys; //foreignrow
        @Offset(64) public int Stat1Min; //i32
        @Offset(68) public int Stat1Max; //i32
        @Offset(72) public int Stat2Min; //i32
        @Offset(76) public int Stat2Max; //i32
        @Offset(112) public int SpawnWeight; //i32
        @Offset(120) public int RandomMin; //i32
        @Offset(124) public int RandomMax; //i32
        @Offset(128) public String FlavourText; //string
        @Offset(136) public String DDSIcon; //string
        @Offset(144) public List<ForeignRow> AchievementItemsKeys; //foreignrow
    }
    public static class AlternateSkillTargetingBehaviours implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public ForeignRow ClientStrings; //foreignrow
    }
    public static class AlternateTreeVersions implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class AnimatedObjectFlags implements DatType{
        @Offset(0) public String AOFile; //string
    }
    public static class Animation implements DatType{
        @Offset(0) public String Id; //string
        @Offset(11) public long Mainhand_AnimationKey; //row
        @Offset(19) public long Offhand_AnimationKey; //row
    }
    public static class ApplyDamageFunctions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> StatsKeys; //foreignrow
    }
    public static class ArchetypeRewards implements DatType{
        @Offset(0) public String Id; //string
        @Offset(40) public ForeignRow Gem; //foreignrow
        @Offset(56) public String BK2File; //string
    }
    public static class Archetypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow CharactersKey; //foreignrow
        @Offset(24) public String PassiveSkillTreeURL; //string
        @Offset(32) public String AscendancyClassName; //string
        @Offset(40) public String Description; //string
        @Offset(48) public String UIImageFile; //string
        @Offset(56) public String TutorialVideo_BKFile; //string
        @Offset(76) public String BackgroundImageFile; //string
        @Offset(84) public boolean IsTemporary; //bool
        @Offset(86) public String ArchetypeImage; //string
    }
    public static class AreaInfluenceDoodads implements DatType{
        @Offset(0) public ForeignRow StatsKey; //foreignrow
        @Offset(16) public int StatValue; //i32
        @Offset(24) public List<String> AOFiles; //string
    }
    public static class AreaTransitionAnimations implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String AnimationId; //string
    }
    public static class AreaTransitionAnimationTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class AreaTransitionInfo implements DatType{
    }
    public static class ArmourTypes implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public int Armour; //i32
        @Offset(20) public int Evasion; //i32
        @Offset(24) public int EnergyShield; //i32
        @Offset(28) public int IncreasedMovementSpeed; //i32
        @Offset(32) public int Ward; //i32
    }
    public static class Ascendancy implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int ClassNo; //i32
        @Offset(12) public List<ForeignRow> Characters; //foreignrow
        @Offset(28) public String CoordinateRect; //string
        @Offset(36) public String RGBFlavourTextColour; //string
        @Offset(44) public String Name; //string
        @Offset(52) public String FlavourText; //string
        @Offset(60) public String OGGFile; //string
        @Offset(68) public String PassiveTreeImage; //string
        @Offset(92) public boolean Disabled; //bool
    }
    public static class AtlasFog implements DatType{
    }
    public static class AtlasInfluenceData implements DatType{
        @Offset(0) public ForeignRow InfluencePack; //foreignrow
        @Offset(16) public List<ForeignRow> MonsterPacks; //foreignrow
    }
    public static class AtlasInfluenceOutcomes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public int Type; //enumrow
    }
    public static class AtlasInfluenceSets implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> InfluencePacks; //foreignrow
    }
    public static class AtlasMods implements DatType{
        @Offset(0) public ForeignRow ModsKey; //foreignrow
        @Offset(16) public int AtlasModTiers; //i32
    }
    public static class AtlasFavouredMapSlots implements DatType{
        @Offset(8) public String Requirement; //string
    }
    public static class AtlasNode implements DatType{
        @Offset(0) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(16) public ForeignRow ItemVisualIdentityKey; //foreignrow
        @Offset(33) public ForeignRow MapsKey; //foreignrow
        @Offset(49) public ForeignRow FlavourTextKey; //foreignrow
        @Offset(65) public List<Long> AtlasNodeKeys; //row
        @Offset(81) public int Tier0; //i32
        @Offset(85) public int Tier1; //i32
        @Offset(89) public int Tier2; //i32
        @Offset(93) public int Tier3; //i32
        @Offset(97) public int Tier4; //i32
        @Offset(121) public String DDSFile; //string
        @Offset(130) public boolean NotOnAtlas; //bool
        @Offset(147) public List<ForeignRow> DivCards; //foreignrow
    }
    public static class AtlasNodeDefinition implements DatType{
        @Offset(0) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(16) public ForeignRow ItemVisualIdentityKey; //foreignrow
        @Offset(33) public int Tier; //i32
    }
    public static class AtlasPositions implements DatType{
        @Offset(8) public float X; //f32
        @Offset(12) public float Y; //f32
    }
    public static class AwardDisplay implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(16) public String BackgroundImage; //string
        @Offset(52) public String ForegroundImage; //string
        @Offset(60) public String OGGFile; //string
    }
    public static class BackendErrors implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
    }
    public static class BaseItemTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow ItemClassesKey; //foreignrow
        @Offset(24) public int Width; //i32
        @Offset(28) public int Height; //i32
        @Offset(32) public String Name; //string
        @Offset(40) public String InheritsFrom; //string
        @Offset(48) public int DropLevel; //i32
        @Offset(52) public ForeignRow FlavourTextKey; //foreignrow
        @Offset(68) public List<ForeignRow> Implicit_ModsKeys; //foreignrow
        @Offset(84) public int SizeOnGround; //i32
        @Offset(88) public ForeignRow SoundEffect; //foreignrow
        @Offset(104) public List<ForeignRow> TagsKeys; //foreignrow
        @Offset(120) public int ModDomain; //enumrow
        @Offset(124) public int SiteVisibility; //i32
        @Offset(128) public ForeignRow ItemVisualIdentity; //foreignrow
        @Offset(144) public int HASH32; //i32
        @Offset(148) public List<ForeignRow> VendorRecipe_AchievementItems; //foreignrow
        @Offset(164) public String Inflection; //string
        @Offset(172) public ForeignRow Equip_AchievementItemsKey; //foreignrow
        @Offset(188) public boolean IsCorrupted; //bool
        @Offset(189) public List<ForeignRow> Identify_AchievementItems; //foreignrow
        @Offset(205) public List<ForeignRow> IdentifyMagic_AchievementItems; //foreignrow
        @Offset(221) public long FragmentBaseItemTypesKey; //row
        @Offset(263) public ForeignRow TradeMarketCategory; //foreignrow
        @Offset(279) public boolean Unmodifiable; //bool
        @Offset(280) public List<ForeignRow> Achievement; //foreignrow
    }
    public static class BindableVirtualKeys implements DatType{
        @Offset(0) public int KeyCode; //i32
        @Offset(4) public String Name; //string
        @Offset(12) public String Id; //string
    }
    public static class BlightStashTabLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow StoredItem; //foreignrow
        @Offset(24) public int XOffset; //i32
        @Offset(28) public int YOffset; //i32
        @Offset(32) public int FirstSlotIndex; //i32
        @Offset(36) public int Width; //i32
        @Offset(40) public int Height; //i32
        @Offset(44) public int SlotSize; //i32
    }
    public static class BloodTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class BuffDefinitions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
        @Offset(16) public boolean Invisible; //bool
        @Offset(17) public boolean Removable; //bool
        @Offset(18) public String Name; //string
        @Offset(26) public List<ForeignRow> StatsKeys; //foreignrow
        @Offset(48) public ForeignRow Maximum_StatsKey; //foreignrow
        @Offset(64) public ForeignRow Current_StatsKey; //foreignrow
        @Offset(85) public ForeignRow BuffVisualsKey; //foreignrow
        @Offset(103) public int BuffCategory; //i32
        @Offset(111) public int BuffLimit; //i32
        @Offset(116) public String Id2; //string
        @Offset(124) public boolean IsRecovery; //bool
        @Offset(204) public List<ForeignRow> BinaryStats; //foreignrow
    }
    public static class BuffTemplates implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BuffDefinitionsKey; //foreignrow
        @Offset(24) public List<Integer> Buff_StatValues; //i32
        @Offset(40) public int AuraRadius; //i32
        @Offset(76) public ForeignRow BuffVisualsKey; //foreignrow
        @Offset(97) public List<ForeignRow> StatsKey; //foreignrow
    }
    public static class BuffVisualOrbArt implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MiscAnimated; //foreignrow
    }
    public static class BuffVisualOrbs implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BuffVisualOrbTypesKey; //foreignrow
        @Offset(24) public List<ForeignRow> BuffVisualOrbArtKeys; //foreignrow
        @Offset(40) public List<ForeignRow> Player_BuffVisualOrbArtKeys; //foreignrow
        @Offset(56) public List<ForeignRow> BuffVisualOrbArtKeys2; //foreignrow
    }
    public static class BuffVisualOrbTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class BuffVisuals implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String BuffDDSFile; //string
        @Offset(16) public List<String> EPKFiles1; //string
        @Offset(32) public List<String> EPKFiles2; //string
        @Offset(48) public List<ForeignRow> PreloadGroups; //foreignrow
        @Offset(65) public String BuffName; //string
        @Offset(73) public ForeignRow MiscAnimated1; //foreignrow
        @Offset(89) public ForeignRow MiscAnimated2; //foreignrow
        @Offset(105) public String BuffDescription; //string
        @Offset(113) public String EPKFile; //string
        @Offset(121) public boolean HasExtraArt; //bool
        @Offset(122) public String ExtraArt; //string
        @Offset(146) public List<String> EPKFiles; //string
        @Offset(162) public List<ForeignRow> BuffVisualOrbs; //foreignrow
        @Offset(178) public ForeignRow MiscAnimated3; //foreignrow
    }
    public static class BuffVisualsArtVariations implements DatType{
        @Offset(0) public String Buff; //string
    }
    public static class BuffVisualSetEntries implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public ForeignRow BuffVisual; //foreignrow
    }
    public static class CharacterAudioEvents implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Event; //foreignrow
        @Offset(32) public List<ForeignRow> Goddess_CharacterTextAudioKeys; //foreignrow
        @Offset(48) public List<ForeignRow> JackTheAxe_CharacterTextAudioKeys; //foreignrow
    }
    public static class CharacterEventTextAudio implements DatType{
        @Offset(0) public ForeignRow Event; //foreignrow
        @Offset(16) public ForeignRow Character; //foreignrow
        @Offset(32) public List<ForeignRow> TextAudio; //foreignrow
    }
    public static class CharacterPanelDescriptionModes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(16) public String FormatString_Positive; //string
        @Offset(24) public String FormatString_Negative; //string
    }
    public static class CharacterPanelStats implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(16) public List<ForeignRow> StatsKeys1; //foreignrow
        @Offset(32) public ForeignRow CharacterPanelDescriptionModesKey; //foreignrow
        @Offset(48) public List<ForeignRow> StatsKeys2; //foreignrow
        @Offset(64) public List<ForeignRow> StatsKeys3; //foreignrow
        @Offset(80) public ForeignRow CharacterPanelTabsKey; //foreignrow
    }
    public static class CharacterPanelTabs implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public String Text; //string
    }
    public static class Characters implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public String AOFile; //string
        @Offset(24) public String ACTFile; //string
        @Offset(32) public int BaseMaxLife; //i32
        @Offset(36) public int BaseMaxMana; //i32
        @Offset(40) public int WeaponSpeed; //i32
        @Offset(44) public int MinDamage; //i32
        @Offset(48) public int MaxDamage; //i32
        @Offset(52) public int MaxAttackDistance; //i32
        @Offset(56) public String Icon; //string
        @Offset(64) public int IntegerId; //i32
        @Offset(68) public int BaseStrength; //i32
        @Offset(72) public int BaseDexterity; //i32
        @Offset(76) public int BaseIntelligence; //i32
        @Offset(96) public String Description; //string
        @Offset(104) public ForeignRow StartSkillGem; //foreignrow
        @Offset(144) public int CharacterSize; //i32
        @Offset(160) public String IntroSoundFile; //string
        @Offset(168) public List<ForeignRow> StartWeapons; //foreignrow
        @Offset(184) public String Gender; //string
        @Offset(192) public String TraitDescription; //string
        @Offset(284) public String PassiveTreeImage; //string
        @Offset(300) public String AttrsAsId; //string
        @Offset(308) public String LoginScreen; //string
        @Offset(316) public String PlayerCritter; //string
        @Offset(324) public String PlayerEffect; //string
        @Offset(332) public String AfterImage; //string
        @Offset(340) public ForeignRow Mirage; //foreignrow
        @Offset(356) public ForeignRow CloneImmobile; //foreignrow
        @Offset(372) public ForeignRow ReplicateClone; //foreignrow
        @Offset(388) public ForeignRow LightningClone; //foreignrow
        @Offset(412) public String SkillTreeBackground; //string
        @Offset(420) public ForeignRow Clone; //foreignrow
        @Offset(452) public ForeignRow MirageWarrior; //foreignrow
        @Offset(468) public ForeignRow DoubleTwo; //foreignrow
        @Offset(484) public ForeignRow DarkExile; //foreignrow
        @Offset(500) public String Attr; //string
        @Offset(508) public String AttrLowercase; //string
        @Offset(516) public String Script; //string
        @Offset(604) public String GemCuttingIcon; //string
    }
    public static class CharacterStartQuestState implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> QuestKeys; //foreignrow
        @Offset(24) public List<Integer> QuestStates; //i32
        @Offset(56) public List<ForeignRow> MapPinsKeys; //foreignrow
    }
    public static class CharacterStartStates implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
        @Offset(16) public ForeignRow CharactersKey; //foreignrow
        @Offset(32) public int Level; //i32
        @Offset(36) public List<ForeignRow> PassiveSkillsKeys; //foreignrow
        @Offset(52) public ForeignRow CharacterStartStateSetKey; //foreignrow
        @Offset(84) public List<ForeignRow> CharacterStartQuestStateKeys; //foreignrow
        @Offset(101) public String InfoText; //string
    }
    public static class CharacterStartStateSet implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class CharacterTextAudio implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(16) public String SoundFile; //string
        @Offset(24) public String ParrotSoundFile; //string
    }
    public static class ChatIcons implements DatType{
        @Offset(0) public String Icon; //string
        @Offset(8) public String Image; //string
    }
    public static class ChestClusters implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> ChestsKeys; //foreignrow
    }
    public static class ChestEffects implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Normal_EPKFile; //string
        @Offset(16) public String Normal_Closed_AOFile; //string
        @Offset(24) public String Normal_Open_AOFile; //string
        @Offset(32) public String Magic_EPKFile; //string
        @Offset(40) public String Unique_EPKFile; //string
        @Offset(48) public String Rare_EPKFile; //string
        @Offset(56) public String Magic_Closed_AOFile; //string
        @Offset(64) public String Unique_Closed_AOFile; //string
        @Offset(72) public String Rare_Closed_AOFile; //string
        @Offset(80) public String Magic_Open_AOFile; //string
        @Offset(88) public String Unique_Open_AOFile; //string
        @Offset(96) public String Rare_Open_AOFile; //string
    }
    public static class Chests implements DatType{
        @Offset(0) public String Id; //string
        @Offset(13) public String Name; //string
        @Offset(21) public List<String> AOFiles; //string
        @Offset(65) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(82) public List<ForeignRow> ModsKeys; //foreignrow
        @Offset(98) public List<ForeignRow> TagsKeys; //foreignrow
        @Offset(114) public ForeignRow ChestEffectsKey; //foreignrow
        @Offset(130) public int MinLevel; //i32
        @Offset(142) public int MaxLevel; //i32
        @Offset(146) public ForeignRow Corrupt_AchievementItemsKey; //foreignrow
        @Offset(162) public ForeignRow CurrencyUse_AchievementItemsKey; //foreignrow
        @Offset(178) public List<ForeignRow> Encounter_AchievementItemsKeys; //foreignrow
        @Offset(210) public String InheritsFrom; //string
        @Offset(318) public boolean IsHardmode; //bool
        @Offset(319) public List<ForeignRow> StatsHardmode; //foreignrow
    }
    public static class ClientStrings implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(16) public String XBoxText; //string
        @Offset(24) public String XBoxText2; //string
        @Offset(32) public int HASH32; //i32
        @Offset(36) public String PlaystationText; //string
    }
    public static class ClientLeagueAction implements DatType{
        @Offset(0) public String Id; //string
        @Offset(28) public String GamepadIcon; //string
    }
    public static class CloneShot implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(20) public ForeignRow MiscAnimated1; //foreignrow
        @Offset(36) public ForeignRow MiscAnimated2; //foreignrow
        @Offset(52) public ForeignRow MiscAnimated3; //foreignrow
    }
    public static class Colours implements DatType{
        @Offset(0) public String Item; //string
        @Offset(8) public int Red; //i32
        @Offset(12) public int Green; //i32
        @Offset(16) public int Blue; //i32
        @Offset(20) public String RgbCode; //string
    }
    public static class Commands implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Command; //string
        @Offset(17) public String EnglishCommand; //string
        @Offset(25) public String Description; //string
    }
    public static class ComponentAttributeRequirements implements DatType{
        @Offset(0) public String BaseItemTypesKey; //string
        @Offset(8) public int ReqStr; //i32
        @Offset(12) public int ReqDex; //i32
        @Offset(16) public int ReqInt; //i32
    }
    public static class ComponentCharges implements DatType{
        @Offset(0) public String BaseItemTypesKey; //string
        @Offset(8) public int MaxCharges; //i32
        @Offset(12) public int PerCharge; //i32
        @Offset(16) public int MaxCharges2; //i32
        @Offset(20) public int PerCharge2; //i32
    }
    public static class CoreLeagues implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class CostTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow StatsKey; //foreignrow
        @Offset(24) public String FormatText; //string
    }
    public static class CraftingBenchOptions implements DatType{
        @Offset(16) public int Order; //i32
        @Offset(20) public ForeignRow AddMod; //foreignrow
        @Offset(36) public List<ForeignRow> Cost_BaseItemTypes; //foreignrow
        @Offset(52) public List<Integer> Cost_Values; //i32
        @Offset(68) public int RequiredLevel; //i32
        @Offset(72) public String Name; //string
        @Offset(80) public int CraftingBenchCustomAction; //enumrow
        @Offset(84) public List<ForeignRow> ItemClasses; //foreignrow
        @Offset(100) public int Links; //i32
        @Offset(104) public String SocketColours; //string
        @Offset(112) public int Sockets; //i32
        @Offset(116) public int ItemQuantity; //i32
        @Offset(136) public String Description; //string
        @Offset(144) public boolean IsDisabled; //bool
        @Offset(145) public boolean IsAreaOption; //bool
        @Offset(146) public List<Integer> RecipeIds; //i32
        @Offset(162) public int Tier; //i32
        @Offset(166) public List<ForeignRow> CraftingItemClassCategories; //foreignrow
        @Offset(186) public ForeignRow UnlockCategory; //foreignrow
        @Offset(202) public int UnveilsRequired; //i32
        @Offset(206) public int UnveilsRequired2; //i32
        @Offset(266) public ForeignRow AddEnchantment; //foreignrow
        @Offset(282) public ForeignRow SortCategory; //foreignrow
    }
    public static class CraftingBenchSortCategories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Name; //foreignrow
        @Offset(24) public boolean IsVisible; //bool
        @Offset(25) public ForeignRow Type; //foreignrow
    }
    public static class CraftingBenchTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class CraftingBenchUnlockCategories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(28) public String UnlockType; //string
        @Offset(36) public List<ForeignRow> CraftingItemClassCategories; //foreignrow
        @Offset(52) public String ObtainingDescription; //string
    }
    public static class CraftingItemClassCategories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> ItemClasses; //foreignrow
        @Offset(32) public String Text; //string
    }
    public static class CurrencyItems implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public int StackSize; //i32
        @Offset(20) public int CurrencyUseType; //i32
        @Offset(24) public String Action; //string
        @Offset(32) public String Directions; //string
        @Offset(40) public ForeignRow FullStack_BaseItemTypesKey; //foreignrow
        @Offset(56) public String Description; //string
        @Offset(64) public List<ForeignRow> Usage_AchievementItemsKeys; //foreignrow
        @Offset(80) public boolean Scroll; //bool
        @Offset(81) public ForeignRow Possession_AchievementItemsKey; //foreignrow
        @Offset(129) public int CurrencyTab_StackSize; //i32
        @Offset(133) public String XBoxDirections; //string
        @Offset(145) public List<ForeignRow> ModifyMapsAchievements; //foreignrow
        @Offset(161) public List<ForeignRow> ModifyContractsAchievements; //foreignrow
        @Offset(177) public List<ForeignRow> CombineAchievements; //foreignrow
        @Offset(193) public boolean ChangedForHardmode; //bool
        @Offset(194) public String DescriptionHardmode; //string
        @Offset(202) public boolean IsGold; //bool
        @Offset(203) public String UsageHint; //string
    }
    public static class CurrencyStashTabLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow StoredItem; //foreignrow
        @Offset(24) public int XOffset; //i32
        @Offset(28) public int YOffset; //i32
        @Offset(32) public int FirstSlotIndex; //i32
        @Offset(36) public int Width; //i32
        @Offset(40) public int Height; //i32
        @Offset(44) public boolean ShowIfEmpty; //bool
        @Offset(45) public int SlotGroup; //i32
    }
    public static class DaemonSpawningData implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> MonsterVarieties; //foreignrow
    }
    public static class DamageHitEffects implements DatType{
        @Offset(0) public int Id; //i32
    }
    public static class DamageParticleEffects implements DatType{
        @Offset(0) public int DamageParticleEffectTypes; //enumrow
        @Offset(4) public int Variation; //i32
        @Offset(8) public String PETFile; //string
        @Offset(16) public ForeignRow ImpactSoundData1; //foreignrow
        @Offset(32) public ForeignRow ImpactSoundData2; //foreignrow
    }
    public static class Dances implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public ForeignRow CharactersKey; //foreignrow
    }
    public static class DaressoPitFights implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class DefaultMonsterStats implements DatType{
        @Offset(0) public String DisplayLevel; //string
        @Offset(8) public float Damage; //f32
        @Offset(12) public int Evasion; //i32
        @Offset(16) public int Accuracy; //i32
        @Offset(20) public int Life; //i32
        @Offset(24) public int Experience; //i32
        @Offset(28) public int AllyLife; //i32
        @Offset(36) public int Difficulty; //i32
        @Offset(40) public float Damage2; //f32
        @Offset(64) public int Armour; //i32
    }
    public static class DeliriumStashTabLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow StoredItem; //foreignrow
        @Offset(24) public int XOffset; //i32
        @Offset(28) public int YOffset; //i32
        @Offset(32) public int FirstSlotIndex; //i32
        @Offset(36) public int Width; //i32
        @Offset(40) public int Height; //i32
        @Offset(44) public int SlotSize; //i32
    }
    public static class DelveStashTabLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow StoredItem; //foreignrow
        @Offset(24) public int XOffset; //i32
        @Offset(28) public int YOffset; //i32
        @Offset(32) public int FirstSlotIndex; //i32
        @Offset(36) public int Width; //i32
        @Offset(40) public int Height; //i32
        @Offset(44) public int SlotSize; //i32
        @Offset(48) public boolean HideIfEmpty; //bool
        @Offset(49) public String Image; //string
    }
    public static class DescentExiles implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(24) public ForeignRow CharactersKey; //foreignrow
        @Offset(40) public ForeignRow MonsterVarietiesKey; //foreignrow
    }
    public static class DescentRewardChests implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> BaseItemTypesKeys1; //foreignrow
        @Offset(24) public List<ForeignRow> BaseItemTypesKeys2; //foreignrow
        @Offset(40) public List<ForeignRow> BaseItemTypesKeys3; //foreignrow
        @Offset(56) public List<ForeignRow> BaseItemTypesKeys4; //foreignrow
        @Offset(72) public List<ForeignRow> BaseItemTypesKeys5; //foreignrow
        @Offset(88) public List<ForeignRow> BaseItemTypesKeys6; //foreignrow
        @Offset(104) public List<ForeignRow> BaseItemTypesKeys7; //foreignrow
        @Offset(120) public List<ForeignRow> BaseItemTypesKeys8; //foreignrow
        @Offset(136) public List<ForeignRow> BaseItemTypesKeys9; //foreignrow
        @Offset(152) public List<ForeignRow> BaseItemTypesKeys10; //foreignrow
        @Offset(168) public List<ForeignRow> BaseItemTypesKeys11; //foreignrow
        @Offset(184) public List<ForeignRow> BaseItemTypesKeys12; //foreignrow
        @Offset(200) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(216) public List<ForeignRow> BaseItemTypesKeys13; //foreignrow
        @Offset(232) public List<ForeignRow> BaseItemTypesKeys14; //foreignrow
    }
    public static class DescentStarterChest implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow CharactersKey; //foreignrow
        @Offset(24) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(40) public String SocketColours; //string
        @Offset(48) public ForeignRow WorldAreasKey; //foreignrow
    }
    public static class DialogueEvent implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int Timer; //i32
    }
    public static class DisplayMinionMonsterType implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public ForeignRow MonsterVarietiesKey; //foreignrow
    }
    public static class DivinationCardStashTabLayout implements DatType{
        @Offset(0) public ForeignRow StoredItem; //foreignrow
        @Offset(16) public boolean IsInGame; //bool
        @Offset(17) public boolean IsEnabled; //bool
    }
    public static class Doors implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class DropEffects implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String AOFile; //string
    }
    public static class DropPool implements DatType{
        @Offset(0) public String Group; //string
        @Offset(8) public int Weight; //i32
        @Offset(28) public int WeightHardmode; //i32
    }
    public static class EclipseMods implements DatType{
        @Offset(0) public String Key; //string
        @Offset(8) public List<ForeignRow> SpawnWeight_TagsKeys; //foreignrow
        @Offset(24) public List<Integer> SpawnWeight_Values; //i32
        @Offset(40) public ForeignRow ModsKey; //foreignrow
        @Offset(56) public int MinLevel; //i32
        @Offset(60) public int MaxLevel; //i32
        @Offset(64) public boolean IsPrefix; //bool
    }
    public static class EffectDrivenSkill implements DatType{
    }
    public static class EffectivenessCostConstants implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public float Multiplier; //f32
    }
    public static class EinharMissions implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class EinharPackFallback implements DatType{
    }
    public static class EndlessLedgeChests implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(24) public List<ForeignRow> BaseItemTypesKeys; //foreignrow
        @Offset(40) public String SocketColour; //string
    }
    public static class Environments implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Base_ENVFile; //string
        @Offset(16) public List<String> Corrupted_ENVFiles; //string
        @Offset(80) public ForeignRow EnvironmentTransitionsKey; //foreignrow
        @Offset(96) public ForeignRow PreloadGroup; //foreignrow
    }
    public static class EnvironmentTransitions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<String> OTFiles; //string
    }
    public static class EssenceStashTabLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow StoredItem; //foreignrow
        @Offset(24) public int XOffset; //i32
        @Offset(28) public int YOffset; //i32
        @Offset(32) public int FirstSlotIndex; //i32
        @Offset(36) public int Width; //i32
        @Offset(40) public int Height; //i32
        @Offset(44) public boolean IsUpgradableEssenceSlot; //bool
    }
    public static class EvergreenAchievements implements DatType{
    }
    public static class ExecuteGEAL implements DatType{
        @Offset(8) public List<ForeignRow> MiscAnimated; //foreignrow
        @Offset(96) public String Script; //string
        @Offset(123) public List<String> MetadataIDs; //string
        @Offset(139) public String ScriptCommand; //string
    }
    public static class ExpandingPulse implements DatType{
        @Offset(0) public int IntId; //i32
        @Offset(4) public String StringId; //string
    }
    public static class ExperienceLevels implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int Level; //i32
        @Offset(12) public int Experience; //u32
    }
    public static class ExplodingStormBuffs implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BuffDefinitionsKey1; //foreignrow
        @Offset(40) public List<Integer> StatValues; //i32
        @Offset(88) public ForeignRow Friendly_MonsterVarietiesKey; //foreignrow
        @Offset(104) public ForeignRow MiscObjectsKey; //foreignrow
        @Offset(120) public ForeignRow MiscAnimatedKey; //foreignrow
        @Offset(136) public ForeignRow BuffVisualsKey; //foreignrow
        @Offset(152) public ForeignRow Enemy_MonsterVarietiesKey; //foreignrow
        @Offset(180) public ForeignRow BuffDefinitionsKey2; //foreignrow
        @Offset(196) public boolean IsOnlySpawningNearPlayer; //bool
    }
    public static class ExtraTerrainFeatures implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<String> ArmFiles; //string
        @Offset(24) public List<String> TdtFiles; //string
        @Offset(81) public ForeignRow WorldAreasKey; //foreignrow
    }
    public static class FixedHideoutDoodadTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> HideoutDoodadsKeys; //foreignrow
        @Offset(24) public ForeignRow BaseTypeHideoutDoodadsKey; //foreignrow
    }
    public static class FixedMissions implements DatType{
    }
    public static class Flasks implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public String Name; //string
        @Offset(24) public int Type; //enumrow
        @Offset(28) public int LifePerUse; //i32
        @Offset(32) public int ManaPerUse; //i32
        @Offset(36) public int RecoveryTime; //i32
        @Offset(40) public int RecoveryTime2; //i32
        @Offset(44) public ForeignRow BuffDefinitionsKey; //foreignrow
        @Offset(64) public List<ForeignRow> UtilityBuff; //foreignrow
    }
    public static class FlavourText implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public short HASH16; //i16
        @Offset(10) public String Text; //string
    }
    public static class Footprints implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<String> Active_AOFiles; //string
        @Offset(24) public List<String> Idle_AOFiles; //string
    }
    public static class FootstepAudio implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int Index; //i32
    }
    public static class FragmentStashTabLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int XOffset; //i32
        @Offset(12) public int YOffset; //i32
        @Offset(16) public int FirstSlotIndex; //i32
        @Offset(20) public int Width; //i32
        @Offset(24) public int Height; //i32
        @Offset(29) public int Tab; //i32
        @Offset(33) public int SlotSize; //i32
        @Offset(37) public boolean HideIfEmpty; //bool
        @Offset(38) public int Subtab; //i32
        @Offset(42) public List<ForeignRow> StoredItems; //foreignrow
    }
    public static class GameConstants implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int Value; //i32
    }
    public static class GameLogos implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String LogoIntl; //string
        @Offset(16) public String LogoTW; //string
    }
    public static class GameObjectTasks implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int HASH16; //i32
    }
    public static class GamepadButton implements DatType{
    }
    public static class GamepadType implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Console; //string
        @Offset(16) public String ImageFile; //string
    }
    public static class GameStats implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Id2; //string
    }
    public static class GemTags implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Tag; //string
        @Offset(16) public ForeignRow Stat1; //foreignrow
        @Offset(32) public ForeignRow Stat2; //foreignrow
        @Offset(48) public ForeignRow Stat3; //foreignrow
    }
    public static class GenericBuffAuras implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class GenericLeagueRewardTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int MinLevel; //i32
        @Offset(12) public int MaxLevel; //i32
    }
    public static class GenericLeagueRewardTypeVisuals implements DatType{
        @Offset(0) public ForeignRow Type; //foreignrow
        @Offset(52) public String Icon; //string
        @Offset(60) public String Name; //string
    }
    public static class GeometryAttack implements DatType{
    }
    public static class GeometryChannel implements DatType{
        @Offset(0) public String Id; //string
        @Offset(114) public String EPKFile; //string
    }
    public static class GeometryProjectiles implements DatType{
    }
    public static class GeometryTrigger implements DatType{
    }
    public static class GiftWrapArtVariations implements DatType{
        @Offset(0) public int Width; //i32
        @Offset(4) public int Height; //i32
        @Offset(12) public ForeignRow Item; //foreignrow
    }
    public static class GlobalAudioConfig implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int Value; //i32
    }
    public static class Grandmasters implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String GMFile; //string
        @Offset(16) public String AISFile; //string
        @Offset(24) public List<ForeignRow> ModsKeys; //foreignrow
        @Offset(40) public int CharacterLevel; //i32
    }
    public static class GrantedEffectQualityStats implements DatType{
        @Offset(0) public ForeignRow GrantedEffectsKey; //foreignrow
        @Offset(16) public List<ForeignRow> StatsKeys; //foreignrow
        @Offset(32) public List<Integer> StatsValuesPermille; //i32
    }
    public static class GrantedEffects implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public boolean IsSupport; //bool
        @Offset(9) public List<ForeignRow> AllowedActiveSkillTypes; //foreignrow
        @Offset(25) public String SupportGemLetter; //string
        @Offset(33) public List<ForeignRow> AddedActiveSkillTypes; //foreignrow
        @Offset(49) public List<ForeignRow> ExcludedActiveSkillTypes; //foreignrow
        @Offset(65) public boolean SupportsGemsOnly; //bool
        @Offset(66) public int HASH32; //i32
        @Offset(70) public boolean CannotBeSupported; //bool
        @Offset(75) public int CastTime; //i32
        @Offset(79) public ForeignRow ActiveSkill; //foreignrow
        @Offset(95) public boolean IgnoreMinionTypes; //bool
        @Offset(97) public List<ForeignRow> AddedMinionActiveSkillTypes; //foreignrow
        @Offset(113) public ForeignRow Animation; //foreignrow
        @Offset(129) public ForeignRow MultiPartAchievement; //foreignrow
        @Offset(146) public List<ForeignRow> SupportWeaponRestrictions; //foreignrow
        @Offset(162) public long RegularVariant; //row
        @Offset(184) public ForeignRow StatSet; //foreignrow
        @Offset(200) public List<ForeignRow> AdditionalStatSets; //foreignrow
        @Offset(224) public List<ForeignRow> CostTypes; //foreignrow
    }
    public static class GrantedEffectsPerLevel implements DatType{
        @Offset(0) public ForeignRow GrantedEffect; //foreignrow
        @Offset(16) public int Level; //i32
        @Offset(20) public int CostMultiplier; //i32
        @Offset(24) public int StoredUses; //i32
        @Offset(28) public int Cooldown; //i32
        @Offset(32) public int CooldownBypassType; //enumrow
        @Offset(36) public int VaalSouls; //i32
        @Offset(40) public int VaalStoredUses; //i32
        @Offset(44) public int CooldownGroup; //i32
        @Offset(52) public int SoulGainPreventionDuration; //i32
        @Offset(56) public int AttackSpeedMultiplier; //i32
        @Offset(84) public int Reservation; //i32
        @Offset(92) public List<Integer> CostAmounts; //i32
    }
    public static class GrantedEffectStatSets implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Label; //foreignrow
        @Offset(24) public List<ForeignRow> ImplicitStats; //foreignrow
        @Offset(40) public List<ForeignRow> ConstantStats; //foreignrow
        @Offset(56) public List<Integer> ConstantStatsValues; //i32
        @Offset(72) public float BaseEffectiveness; //f32
        @Offset(76) public float IncrementalEffectiveness; //f32
    }
    public static class GrantedEffectStatSetsPerLevel implements DatType{
        @Offset(0) public ForeignRow StatSet; //foreignrow
        @Offset(16) public int GemLevel; //i32
        @Offset(20) public int SpellCritChance; //i32
        @Offset(24) public int AttackCritChance; //i32
        @Offset(28) public List<Integer> BaseResolvedValues; //i32
        @Offset(44) public List<Integer> AdditionalStatsValues; //i32
        @Offset(60) public List<ForeignRow> GrantedEffects; //foreignrow
        @Offset(81) public List<ForeignRow> AdditionalFlags; //foreignrow
        @Offset(97) public List<ForeignRow> FloatStats; //foreignrow
        @Offset(113) public List<ForeignRow> InterpolationBases; //foreignrow
        @Offset(129) public List<ForeignRow> AdditionalStats; //foreignrow
        @Offset(145) public List<Integer> StatInterpolations; //enumrow
        @Offset(161) public List<Float> FloatStatsValues; //f32
        @Offset(181) public int BaseMultiplier; //i32
    }
    public static class GroundEffects implements DatType{
        @Offset(0) public ForeignRow GroundEffectTypesKey; //foreignrow
        @Offset(61) public List<String> AOFile; //string
        @Offset(93) public String EndEffect; //string
    }
    public static class GroundEffectTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class HeistStorageLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow StoredItem; //foreignrow
        @Offset(25) public String ButtonFile; //string
        @Offset(33) public int SlotSize; //i32
        @Offset(37) public ForeignRow HeistJobsKey; //foreignrow
        @Offset(53) public int Width; //i32
        @Offset(57) public int Height; //i32
        @Offset(77) public ForeignRow ItemClass; //foreignrow
    }
    public static class HideoutDoodads implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public List<String> Variation_AOFiles; //string
        @Offset(32) public boolean IsNonMasterDoodad; //bool
        @Offset(33) public String InheritsFrom; //string
        @Offset(42) public boolean IsCraftingBench; //bool
        @Offset(43) public List<ForeignRow> Tags; //foreignrow
        @Offset(76) public ForeignRow Category; //foreignrow
    }
    public static class HideoutDoodadCategory implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
    }
    public static class HideoutDoodadTags implements DatType{
        @Offset(0) public String Id; //string
        @Offset(40) public String Name; //string
    }
    public static class HideoutNPCs implements DatType{
        @Offset(0) public ForeignRow Hideout_NPCsKey; //foreignrow
        @Offset(16) public List<ForeignRow> Regular_NPCsKeys; //foreignrow
        @Offset(32) public ForeignRow HideoutDoodadsKey; //foreignrow
        @Offset(48) public int NPCMasterKey; //i32
    }
    public static class HideoutRarity implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
    }
    public static class Hideouts implements DatType{
        @Offset(0) public ForeignRow HideoutArea; //foreignrow
        @Offset(16) public short HASH16; //i16
        @Offset(18) public String HideoutFile; //string
        @Offset(26) public List<ForeignRow> SpawnAreas; //foreignrow
        @Offset(42) public ForeignRow ClaimSideArea; //foreignrow
        @Offset(58) public String HideoutImage; //string
        @Offset(66) public boolean IsEnabled; //bool
        @Offset(67) public int Weight; //i32
        @Offset(71) public ForeignRow Rarity; //foreignrow
        @Offset(87) public boolean NotActsArea; //bool
        @Offset(88) public String Name; //string
        @Offset(116) public String ErrorMessage; //string
    }
    public static class ImpactSoundData implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Sound; //string
    }
    public static class IndexableSkillGems implements DatType{
        @Offset(0) public int Index; //i32
        @Offset(4) public List<ForeignRow> SkillGem1; //foreignrow
        @Offset(20) public String Name1; //string
        @Offset(28) public List<ForeignRow> SkillGem2; //foreignrow
        @Offset(44) public String Name2; //string
    }
    public static class IndexableSupportGems implements DatType{
        @Offset(0) public int Index; //i32
        @Offset(4) public ForeignRow SupportGem; //foreignrow
        @Offset(20) public String Name; //string
    }
    public static class InfluenceExalts implements DatType{
        @Offset(0) public int Influence; //enumrow
        @Offset(4) public ForeignRow BaseItemTypesKey; //foreignrow
    }
    public static class InfluenceTags implements DatType{
        @Offset(0) public ForeignRow ItemClass; //foreignrow
        @Offset(16) public int Influence; //enumrow
        @Offset(20) public ForeignRow Tag; //foreignrow
    }
    public static class Inventories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int InventoryIdKey; //i32
        @Offset(12) public int InventoryTypeKey; //enumrow
    }
    public static class ItemClassCategories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
    }
    public static class ItemClasses implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public ForeignRow TradeMarketCategory; //foreignrow
        @Offset(32) public ForeignRow ItemClassCategory; //foreignrow
        @Offset(48) public boolean RemovedIfLeavesArea; //bool
        @Offset(65) public List<ForeignRow> IdentifyAchievements; //foreignrow
        @Offset(81) public boolean AllocateToMapOwner; //bool
        @Offset(82) public boolean AlwaysAllocate; //bool
        @Offset(83) public boolean CanHaveVeiledMods; //bool
        @Offset(84) public ForeignRow PickedUpQuest; //foreignrow
        @Offset(104) public boolean AlwaysShow; //bool
        @Offset(105) public boolean CanBeCorrupted; //bool
        @Offset(106) public boolean CanHaveIncubators; //bool
        @Offset(107) public boolean CanHaveInfluence; //bool
        @Offset(108) public boolean CanBeDoubleCorrupted; //bool
        @Offset(109) public boolean CanHaveAspects; //bool
        @Offset(110) public boolean CanTransferSkin; //bool
        @Offset(111) public ForeignRow ItemStance; //foreignrow
        @Offset(127) public boolean CanScourge; //bool
        @Offset(128) public boolean CanUpgradeRarity; //bool
        @Offset(130) public List<Integer> InventoryDimensions; //i32
        @Offset(146) public List<Integer> ItemClassFlags; //i32
        @Offset(162) public boolean Unmodfiable; //bool
        @Offset(163) public boolean CanBeFractured; //bool
        @Offset(180) public boolean UsableInMapDevice; //bool
    }
    public static class ItemCostPerLevel implements DatType{
        @Offset(0) public ForeignRow Contract_BaseItemTypesKey; //foreignrow
        @Offset(16) public int Level; //i32
        @Offset(20) public ForeignRow Cost; //foreignrow
        @Offset(36) public ForeignRow CostHardmode; //foreignrow
    }
    public static class ItemCosts implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> Cost1Currencies; //foreignrow
        @Offset(24) public List<Integer> Cost1Values; //i32
        @Offset(40) public List<ForeignRow> Cost2Currencies; //foreignrow
        @Offset(56) public List<Integer> Cost2Values; //i32
        @Offset(72) public List<ForeignRow> Cost3Currencies; //foreignrow
        @Offset(88) public List<Integer> Cost3Values; //i32
        @Offset(104) public List<ForeignRow> Cost4Currencies; //foreignrow
        @Offset(120) public List<Integer> Cost4Values; //i32
    }
    public static class ItemFrameType implements DatType{
        @Offset(0) public String Id; //string
        @Offset(9) public boolean DoubleLine; //bool
        @Offset(10) public String HeaderSingle; //string
        @Offset(18) public String HeaderDouble; //string
        @Offset(26) public String HardmodeHeaderSingle; //string
        @Offset(34) public String HardmodeHeaderDouble; //string
        @Offset(42) public List<Integer> Color; //i32
        @Offset(58) public String Separator; //string
        @Offset(67) public ForeignRow Rarity; //foreignrow
        @Offset(83) public ForeignRow DisplayString; //foreignrow
        @Offset(99) public String ColorMarkup; //string
    }
    public static class ItemExperienceTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class ItemExperiencePerLevel implements DatType{
        @Offset(0) public ForeignRow ItemExperienceType; //foreignrow
        @Offset(16) public int ItemCurrentLevel; //i32
        @Offset(20) public int Experience; //i32
        @Offset(24) public int Level; //i32
    }
    public static class ItemisedVisualEffect implements DatType{
        @Offset(0) public ForeignRow EffectBaseType; //foreignrow
        @Offset(16) public ForeignRow VisualEffect; //foreignrow
        @Offset(32) public ForeignRow VisualIdentity; //foreignrow
        @Offset(48) public List<ForeignRow> Stats; //foreignrow
        @Offset(64) public List<ForeignRow> ItemClasses; //foreignrow
    }
    public static class ItemNoteCode implements DatType{
        @Offset(0) public ForeignRow BaseItem; //foreignrow
        @Offset(16) public String Code; //string
        @Offset(24) public int Order1; //i32
        @Offset(28) public boolean Show; //bool
        @Offset(29) public int Order2; //i32
    }
    public static class ItemStances implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class ItemVisualEffect implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String DaggerEPKFile; //string
        @Offset(16) public String BowEPKFile; //string
        @Offset(24) public String OneHandedMaceEPKFile; //string
        @Offset(32) public String OneHandedSwordEPKFile; //string
        @Offset(48) public String TwoHandedSwordEPKFile; //string
        @Offset(56) public String TwoHandedStaffEPKFile; //string
        @Offset(64) public short HASH16; //i16
        @Offset(66) public String TwoHandedMaceEPKFile; //string
        @Offset(74) public String OneHandedAxeEPKFile; //string
        @Offset(82) public String TwoHandedAxeEPKFile; //string
        @Offset(90) public String ClawEPKFile; //string
        @Offset(98) public String PETFile; //string
        @Offset(106) public List<String> Shield; //string
    }
    public static class ItemVisualHeldBodyModel implements DatType{
        @Offset(0) public ForeignRow ItemVisualIdentity; //foreignrow
        @Offset(16) public String MarauderAnimatedObject; //string
        @Offset(24) public String RangerAnimatedObject; //string
        @Offset(32) public String WitchAnimatedObject; //string
        @Offset(40) public String DuelistAnimatedObject; //string
        @Offset(48) public String TemplarAnimatedObject; //string
        @Offset(56) public String ShadowAnimatedObject; //string
        @Offset(64) public String ScionAnimatedObject; //string
        @Offset(72) public String MarauderBone; //string
        @Offset(80) public String RangerBone; //string
        @Offset(88) public String WitchBone; //string
        @Offset(96) public String DuelistBone; //string
        @Offset(104) public String TemplarBone; //string
        @Offset(112) public String ShadowBone; //string
        @Offset(120) public String ScionBone; //string
    }
    public static class ItemVisualIdentity implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String DDSFile; //string
        @Offset(16) public String AOFile; //string
        @Offset(24) public ForeignRow InventorySoundEffect; //foreignrow
        @Offset(40) public short HASH16; //i16
        @Offset(42) public String AOFile2; //string
        @Offset(50) public List<String> MarauderSMFiles; //string
        @Offset(66) public List<String> RangerSMFiles; //string
        @Offset(82) public List<String> WitchSMFiles; //string
        @Offset(98) public List<String> DuelistDexSMFiles; //string
        @Offset(114) public List<String> TemplarSMFiles; //string
        @Offset(130) public List<String> ShadowSMFiles; //string
        @Offset(146) public List<String> ScionSMFiles; //string
        @Offset(162) public String MarauderShape; //string
        @Offset(170) public String RangerShape; //string
        @Offset(178) public String WitchShape; //string
        @Offset(186) public String DuelistShape; //string
        @Offset(194) public String TemplarShape; //string
        @Offset(202) public String ShadowShape; //string
        @Offset(210) public String ScionShape; //string
        @Offset(238) public List<ForeignRow> Pickup_AchievementItemsKeys; //foreignrow
        @Offset(254) public List<String> SMFiles; //string
        @Offset(270) public List<ForeignRow> Identify_AchievementItemsKeys; //foreignrow
        @Offset(286) public String EPKFile; //string
        @Offset(294) public List<ForeignRow> Corrupt_AchievementItemsKeys; //foreignrow
        @Offset(310) public boolean IsAlternateArt; //bool
        @Offset(312) public ForeignRow CreateCorruptedJewelAchievementItemsKey; //foreignrow
        @Offset(328) public String AnimationLocation; //string
        @Offset(432) public boolean IsAtlasOfWorldsMapIcon; //bool
        @Offset(433) public boolean IsTier16Icon; //bool
        @Offset(651) public int Composition; //i32
    }
    public static class JobAssassinationSpawnerGroups implements DatType{
    }
    public static class JobRaidBrackets implements DatType{
        @Offset(0) public int MinLevel; //i32
        @Offset(8) public ForeignRow WorldArea; //foreignrow
    }
    public static class KillstreakThresholds implements DatType{
        @Offset(0) public int Kills; //i32
        @Offset(4) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(20) public ForeignRow AchievementItemsKey; //foreignrow
    }
    public static class LeagueFlag implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Image; //string
        @Offset(16) public boolean IsHC; //bool
        @Offset(17) public boolean IsSSF; //bool
        @Offset(18) public String Banner; //string
        @Offset(26) public boolean IsRuthless; //bool
    }
    public static class LeagueInfo implements DatType{
        @Offset(0) public ForeignRow PanelVersion; //foreignrow
        @Offset(16) public String PanelId; //string
        @Offset(24) public String PanelImage; //string
        @Offset(32) public String HeaderImage; //string
        @Offset(40) public List<String> Screenshots; //string
        @Offset(56) public String Description; //string
        @Offset(65) public String TrailerVideoLink; //string
        @Offset(73) public String BackgroundImage; //string
        @Offset(83) public List<String> PanelItems; //string
    }
    public static class LeagueInfoPanelVersions implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class LeagueProgressQuestFlags implements DatType{
        @Offset(0) public ForeignRow QuestFlag; //foreignrow
        @Offset(16) public ForeignRow CompletionString; //foreignrow
        @Offset(32) public String Boss; //string
    }
    public static class LeagueStaticRewards implements DatType{
    }
    public static class LevelRelativePlayerScaling implements DatType{
        @Offset(0) public int PlayerLevel; //i32
        @Offset(4) public int MonsterLevel; //i32
    }
    public static class MagicMonsterLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int Life; //i32
    }
    public static class MapCompletionAchievements implements DatType{
        @Offset(8) public List<ForeignRow> MapStatConditionsKeys; //foreignrow
        @Offset(24) public List<ForeignRow> StatsKeys; //foreignrow
        @Offset(40) public List<ForeignRow> AchievementItemsKeys; //foreignrow
        @Offset(56) public List<ForeignRow> MapTierAchievementsKeys; //foreignrow
        @Offset(73) public List<ForeignRow> WorldAreasKeys; //foreignrow
    }
    public static class MapConnections implements DatType{
        @Offset(0) public ForeignRow MapPinsKey0; //foreignrow
        @Offset(16) public ForeignRow MapPinsKey1; //foreignrow
        @Offset(48) public String RestrictedAreaText; //string
    }
    public static class MapCreationInformation implements DatType{
        @Offset(0) public ForeignRow MapsKey; //foreignrow
        @Offset(16) public int Tier; //i32
    }
    public static class MapDeviceRecipes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> RecipeItems; //foreignrow
        @Offset(24) public ForeignRow WorldArea; //foreignrow
        @Offset(40) public ForeignRow MicrotransactionPortalVariation; //foreignrow
        @Offset(56) public int AreaLevel; //i32
        @Offset(83) public List<ForeignRow> OpenAchievemnts; //foreignrow
    }
    public static class MapDevices implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MiscObject; //foreignrow
    }
    public static class MapFragmentMods implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public List<ForeignRow> ModsKeys; //foreignrow
        @Offset(32) public List<ForeignRow> AchievementItemsKey; //foreignrow
        @Offset(48) public int MapFragmentFamilies; //i32
        @Offset(58) public int MinAreaLevel; //i32
        @Offset(66) public int MapFragmentLimit; //i32
    }
    public static class MapInhabitants implements DatType{
        @Offset(0) public ForeignRow StatsKey; //foreignrow
        @Offset(16) public List<ForeignRow> MonsterPacksKeys; //foreignrow
    }
    public static class MapPins implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int PositionX; //i32
        @Offset(12) public int PositionY; //i32
        @Offset(16) public ForeignRow Waypoint_WorldAreasKey; //foreignrow
        @Offset(32) public List<ForeignRow> WorldAreasKeys; //foreignrow
        @Offset(48) public String Name; //string
        @Offset(56) public String FlavourText; //string
        @Offset(80) public int Act; //i32
    }
    public static class MapPurchaseCosts implements DatType{
        @Offset(0) public int Tier; //i32
        @Offset(4) public ForeignRow Cost; //foreignrow
    }
    public static class Maps implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public ForeignRow Regular_WorldAreasKey; //foreignrow
        @Offset(32) public List<ForeignRow> MonsterPacksKeys; //foreignrow
        @Offset(48) public ForeignRow AchievementItem; //foreignrow
        @Offset(72) public int Tier; //i32
        @Offset(76) public int MapSeriesKey; //i32
    }
    public static class MapSeries implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public String BaseIcon_DDSFile; //string
        @Offset(24) public String Infected_DDSFile; //string
        @Offset(32) public String Shaper_DDSFile; //string
        @Offset(40) public String Elder_DDSFile; //string
        @Offset(48) public String Drawn_DDSFile; //string
        @Offset(56) public String Delirious_DDSFile; //string
        @Offset(64) public String UberBlight_DDSFile; //string
    }
    public static class MapSeriesTiers implements DatType{
        @Offset(0) public ForeignRow MapsKey; //foreignrow
        @Offset(16) public int MapWorldsTier; //i32
        @Offset(20) public int BetrayalTier; //i32
        @Offset(24) public int SynthesisTier; //i32
        @Offset(28) public int LegionTier; //i32
        @Offset(32) public int BlightTier; //i32
        @Offset(36) public int MetamorphosisTier; //i32
        @Offset(40) public int DeliriumTier; //i32
        @Offset(44) public int HarvestTier; //i32
        @Offset(48) public int HeistTier; //i32
        @Offset(52) public int RitualTier; //i32
        @Offset(56) public int ExpeditionTier; //i32
        @Offset(60) public int ScourgeTier; //i32
        @Offset(64) public int ArchnemesisTier; //i32
        @Offset(68) public int SentinelTier; //i32
        @Offset(72) public int KalandraTier; //i32
        @Offset(76) public int SanctumTier; //i32
        @Offset(80) public int CrucibleTier; //i32
        @Offset(84) public int AncestorTier; //i32
        @Offset(88) public int AzmeriTier; //i32
        @Offset(92) public int NecropolisTier; //i32
        @Offset(96) public int SettlersTier; //i32
    }
    public static class MapStashSpecialTypeEntries implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public ForeignRow MapItem; //foreignrow
        @Offset(28) public String Name; //string
        @Offset(40) public boolean IsShaperGuardian; //bool
        @Offset(41) public boolean IsElderGuardian; //bool
    }
    public static class MapStashUniqueMapInfo implements DatType{
        @Offset(0) public ForeignRow UniqueMap; //foreignrow
        @Offset(16) public ForeignRow BaseItem; //foreignrow
    }
    public static class MapStatConditions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow StatsKey; //foreignrow
        @Offset(25) public int StatMin; //i32
        @Offset(29) public int StatMax; //i32
    }
    public static class MapTierAchievements implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> AchievementItemsKey; //foreignrow
        @Offset(24) public List<Integer> MapTiers; //i32
    }
    public static class MapTiers implements DatType{
        @Offset(0) public int Tier; //i32
        @Offset(4) public int Level; //i32
        @Offset(8) public int Level2; //i32
    }
    public static class Melee implements DatType{
        @Offset(0) public ForeignRow ActiveSkill; //foreignrow
        @Offset(20) public ForeignRow MiscAnimated; //foreignrow
        @Offset(36) public ForeignRow MeleeTrailsKey1; //foreignrow
        @Offset(52) public ForeignRow MeleeTrailsKey2; //foreignrow
        @Offset(68) public ForeignRow MeleeTrailsKey3; //foreignrow
        @Offset(84) public ForeignRow MeleeTrailsKey4; //foreignrow
        @Offset(100) public ForeignRow MeleeTrailsKey5; //foreignrow
        @Offset(116) public ForeignRow MeleeTrailsKey6; //foreignrow
        @Offset(132) public ForeignRow MeleeTrailsKey7; //foreignrow
        @Offset(149) public String SurgeEffect_EPKFile; //string
    }
    public static class MeleeTrails implements DatType{
        @Offset(0) public String EPKFile1; //string
        @Offset(12) public String EPKFile2; //string
        @Offset(33) public String AOFile; //string
    }
    public static class MetamorphosisStashTabLayout implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow StoredItem; //foreignrow
        @Offset(24) public int XOffset; //i32
        @Offset(28) public int YOffset; //i32
        @Offset(32) public int FirstSlotIndex; //i32
        @Offset(36) public int Width; //i32
        @Offset(40) public int Height; //i32
        @Offset(44) public int SlotSize; //i32
    }
    public static class MicroMigrationData implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
    }
    public static class MicrotransactionCategory implements DatType{
        @Offset(0) public int Id; //enumrow
        @Offset(4) public String Name; //string
    }
    public static class MicrotransactionCharacterPortraitVariations implements DatType{
        @Offset(0) public ForeignRow BaseItemType; //foreignrow
    }
    public static class MicrotransactionCombineFormula implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Result_BaseItemTypesKey; //foreignrow
        @Offset(24) public List<ForeignRow> Ingredients_BaseItemTypesKeys; //foreignrow
        @Offset(40) public String BK2File; //string
    }
    public static class MicrotransactionCursorVariations implements DatType{
    }
    public static class MicrotransactionFireworksVariations implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public String AOFile; //string
    }
    public static class MicrotransactionPeriodicCharacterEffectVariations implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String AOFile; //string
        @Offset(16) public ForeignRow MiscObject; //foreignrow
    }
    public static class MicrotransactionPortalVariations implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public int Id; //i32
        @Offset(20) public String AOFile; //string
        @Offset(28) public String MapAOFile; //string
        @Offset(40) public ForeignRow MiscObject; //foreignrow
        @Offset(56) public String PortalEffect; //string
        @Offset(76) public String PortalEffectLarge; //string
        @Offset(92) public String Script; //string
    }
    public static class MicrotransactionRarityDisplay implements DatType{
        @Offset(0) public String Rarity; //string
    }
    public static class MicrotransactionRecycleOutcomes implements DatType{
    }
    public static class MicrotransactionRecycleSalvageValues implements DatType{
        @Offset(0) public ForeignRow BaseItemType; //foreignrow
    }
    public static class MicrotransactionSlot implements DatType{
        @Offset(0) public int Id; //enumrow
        @Offset(20) public String Name; //string
    }
    public static class MicrotransactionSocialFrameVariations implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public int Id; //i32
        @Offset(20) public String BK2File; //string
    }
    public static class MinimapIcons implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int MinimapIconRadius; //i32
        @Offset(12) public int LargemapIconRadius; //i32
        @Offset(19) public int MinimapIconPointerMaxDistance; //i32
    }
    public static class MiniQuestStates implements DatType{
    }
    public static class MiscAnimated implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String AOFile; //string
        @Offset(16) public List<ForeignRow> PreloadGroupsKeys; //foreignrow
        @Offset(40) public int HASH32; //i32
    }
    public static class MiscAnimatedArtVariations implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MiscBeams implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MiscAnimated; //foreignrow
        @Offset(28) public List<ForeignRow> PreloadGroupsKeys; //foreignrow
    }
    public static class MiscBeamsArtVariations implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MiscEffectPacks implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String EPKFile; //string
        @Offset(28) public List<ForeignRow> PreloadGroups; //foreignrow
        @Offset(45) public String PlayerOnly_EPKFile; //string
    }
    public static class MiscEffectPacksArtVariations implements DatType{
    }
    public static class MiscObjects implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String EffectVirtualPath; //string
        @Offset(16) public List<ForeignRow> PreloadGroupsKeys; //foreignrow
    }
    public static class MiscObjectsArtVariations implements DatType{
    }
    public static class MissionTimerTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Image; //string
        @Offset(16) public String BackgroundImage; //string
    }
    public static class MissionTransitionTiles implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String TDTFile; //string
    }
    public static class ModEffectStats implements DatType{
        @Offset(0) public ForeignRow StatsKey; //foreignrow
        @Offset(16) public List<ForeignRow> TagsKeys; //foreignrow
    }
    public static class ModEquivalencies implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow ModsKey0; //foreignrow
        @Offset(24) public ForeignRow ModsKey1; //foreignrow
        @Offset(40) public ForeignRow ModsKey2; //foreignrow
    }
    public static class ModFamily implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class Mods implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public short HASH16; //u16
        @Offset(10) public ForeignRow ModTypeKey; //foreignrow
        @Offset(26) public int Level; //i32
        @Offset(30) public ForeignRow StatsKey1; //foreignrow
        @Offset(46) public ForeignRow StatsKey2; //foreignrow
        @Offset(62) public ForeignRow StatsKey3; //foreignrow
        @Offset(78) public ForeignRow StatsKey4; //foreignrow
        @Offset(94) public int Domain; //enumrow
        @Offset(98) public String Name; //string
        @Offset(106) public int GenerationType; //enumrow
        @Offset(110) public List<ForeignRow> Families; //foreignrow
        @Offset(126) public int Stat1Min; //i32
        @Offset(130) public int Stat1Max; //i32
        @Offset(134) public int Stat2Min; //i32
        @Offset(138) public int Stat2Max; //i32
        @Offset(142) public int Stat3Min; //i32
        @Offset(146) public int Stat3Max; //i32
        @Offset(150) public int Stat4Min; //i32
        @Offset(154) public int Stat4Max; //i32
        @Offset(158) public List<ForeignRow> SpawnWeight_TagsKeys; //foreignrow
        @Offset(174) public List<Integer> SpawnWeight_Values; //i32
        @Offset(190) public List<ForeignRow> TagsKeys; //foreignrow
        @Offset(206) public List<ForeignRow> GrantedEffectsPerLevelKeys; //foreignrow
        @Offset(238) public String MonsterMetadata; //string
        @Offset(246) public List<ForeignRow> MonsterKillAchievements; //foreignrow
        @Offset(262) public List<ForeignRow> ChestModType; //foreignrow
        @Offset(278) public int Stat5Min; //i32
        @Offset(282) public int Stat5Max; //i32
        @Offset(286) public ForeignRow StatsKey5; //foreignrow
        @Offset(302) public List<ForeignRow> FullAreaClear_AchievementItemsKey; //foreignrow
        @Offset(318) public List<ForeignRow> AchievementItemsKey; //foreignrow
        @Offset(334) public List<ForeignRow> GenerationWeight_TagsKeys; //foreignrow
        @Offset(350) public List<Integer> GenerationWeight_Values; //i32
        @Offset(366) public List<ForeignRow> ModifyMapsAchievements; //foreignrow
        @Offset(382) public boolean IsEssenceOnlyModifier; //bool
        @Offset(383) public int Stat6Min; //i32
        @Offset(387) public int Stat6Max; //i32
        @Offset(391) public ForeignRow StatsKey6; //foreignrow
        @Offset(407) public int MaxLevel; //i32
        @Offset(412) public List<ForeignRow> CraftingItemClassRestrictions; //foreignrow
        @Offset(428) public String MonsterOnDeath; //string
        @Offset(456) public int Heist_SubStatValue1; //i32
        @Offset(460) public int Heist_SubStatValue2; //i32
        @Offset(464) public ForeignRow Heist_StatsKey0; //foreignrow
        @Offset(480) public ForeignRow Heist_StatsKey1; //foreignrow
        @Offset(496) public int Heist_AddStatValue1; //i32
        @Offset(500) public int Heist_AddStatValue2; //i32
        @Offset(504) public int InfluenceTypes; //enumrow
        @Offset(508) public List<ForeignRow> ImplicitTagsKeys; //foreignrow
        @Offset(589) public ForeignRow BuffTemplate; //foreignrow
        @Offset(605) public long ArchnemesisMinionMod; //row
        @Offset(613) public int HASH32; //i32
        @Offset(653) public int RadiusJewelType; //i32
    }
    public static class ModSellPriceTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class ModType implements DatType{
        @Offset(0) public String Name; //string
        @Offset(8) public List<ForeignRow> ModSellPriceTypesKeys; //foreignrow
    }
    public static class MonsterArmours implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String ArtString_SMFile; //string
    }
    public static class MonsterBonuses implements DatType{
        @Offset(0) public String Id; //string
        @Offset(56) public List<ForeignRow> StatsKeys; //foreignrow
        @Offset(72) public List<Integer> StatValues; //i32
    }
    public static class MonsterConditionalEffectPacks implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> MiscEffectPack1; //foreignrow
        @Offset(24) public List<ForeignRow> MiscEffectPack2; //foreignrow
        @Offset(40) public List<ForeignRow> MiscEffectPack3; //foreignrow
        @Offset(56) public List<ForeignRow> MiscEffectPack4; //foreignrow
    }
    public static class MonsterConditions implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MonsterDeathAchievements implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> MonsterVarietiesKeys; //foreignrow
        @Offset(24) public List<ForeignRow> AchievementItemsKeys; //foreignrow
        @Offset(41) public List<ForeignRow> PlayerConditionsKeys; //foreignrow
        @Offset(57) public List<ForeignRow> MonsterDeathConditionsKeys; //foreignrow
        @Offset(183) public List<ForeignRow> NearbyMonsterConditionsKeys; //foreignrow
        @Offset(200) public List<ForeignRow> MultiPartAchievementConditionsKeys; //foreignrow
    }
    public static class MonsterDeathConditions implements DatType{
    }
    public static class MonsterGroupEntries implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(24) public int MonsterGroupNamesId; //i32
    }
    public static class MonsterHeightBrackets implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public ForeignRow BuffVisuals1; //foreignrow
        @Offset(28) public ForeignRow BuffVisuals2; //foreignrow
        @Offset(56) public ForeignRow Tag; //foreignrow
    }
    public static class MonsterHeights implements DatType{
        @Offset(0) public ForeignRow MonsterVariety; //foreignrow
        @Offset(20) public ForeignRow MonsterHeightBracket; //foreignrow
    }
    public static class MonsterMapBossDifficulty implements DatType{
        @Offset(0) public int MapLevel; //i32
        @Offset(4) public int Stat1Value; //i32
        @Offset(8) public int Stat2Value; //i32
        @Offset(12) public ForeignRow StatsKey1; //foreignrow
        @Offset(28) public ForeignRow StatsKey2; //foreignrow
        @Offset(44) public ForeignRow StatsKey3; //foreignrow
        @Offset(60) public int Stat3Value; //i32
        @Offset(64) public ForeignRow StatsKey4; //foreignrow
        @Offset(80) public int Stat4Value; //i32
        @Offset(84) public ForeignRow StatsKey5; //foreignrow
        @Offset(100) public int Stat5Value; //i32
    }
    public static class MonsterMapDifficulty implements DatType{
        @Offset(0) public int MapLevel; //i32
        @Offset(4) public int Stat1Value; //i32
        @Offset(8) public int Stat2Value; //i32
        @Offset(12) public ForeignRow StatsKey1; //foreignrow
        @Offset(28) public ForeignRow StatsKey2; //foreignrow
        @Offset(44) public ForeignRow StatsKey3; //foreignrow
        @Offset(60) public int Stat3Value; //i32
        @Offset(64) public ForeignRow StatsKey4; //foreignrow
        @Offset(80) public int Stat4Value; //i32
    }
    public static class MonsterMortar implements DatType{
        @Offset(0) public int Id; //i32
    }
    public static class MonsterPackCounts implements DatType{
    }
    public static class MonsterPackEntries implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MonsterPacksKey; //foreignrow
        @Offset(29) public ForeignRow MonsterVarietiesKey; //foreignrow
    }
    public static class MonsterPacks implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public List<ForeignRow> WorldAreasKeys; //foreignrow
        @Offset(36) public int BossMonsterSpawnChance; //i32
        @Offset(40) public int BossMonsterCount; //i32
        @Offset(44) public List<ForeignRow> BossMonster_MonsterVarietiesKeys; //foreignrow
        @Offset(93) public List<ForeignRow> TagsKeys; //foreignrow
        @Offset(109) public int MinLevel; //i32
        @Offset(113) public int MaxLevel; //i32
        @Offset(117) public List<ForeignRow> WorldAreas2; //foreignrow
        @Offset(137) public ForeignRow PackFormation; //foreignrow
        @Offset(194) public ForeignRow NecropolisPack; //foreignrow
    }
    public static class MonsterProjectileAttack implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public ForeignRow Projectile; //foreignrow
    }
    public static class MonsterProjectileSpell implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public ForeignRow Projectile; //foreignrow
        @Offset(20) public ForeignRow Animation; //foreignrow
    }
    public static class MonsterResistances implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int FireNormal; //i32
        @Offset(12) public int ColdNormal; //i32
        @Offset(16) public int LightningNormal; //i32
        @Offset(20) public int ChaosNormal; //i32
        @Offset(24) public int FireCruel; //i32
        @Offset(28) public int ColdCruel; //i32
        @Offset(32) public int LightningCruel; //i32
        @Offset(36) public int ChaosCruel; //i32
        @Offset(40) public int FireMerciless; //i32
        @Offset(44) public int ColdMerciless; //i32
        @Offset(48) public int LightningMerciless; //i32
        @Offset(52) public int ChaosMerciless; //i32
    }
    public static class MonsterSegments implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Shapes; //string
    }
    public static class MonsterSpawnerGroups implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MonsterSpawnerGroupsPerLevel implements DatType{
        @Offset(0) public ForeignRow MonsterSpawnerGroupsKey; //foreignrow
        @Offset(16) public int MinLevel; //i32
    }
    public static class MonsterSpawnerOverrides implements DatType{
        @Offset(16) public ForeignRow Base_MonsterVarietiesKey; //foreignrow
        @Offset(32) public ForeignRow Override_MonsterVarietiesKey; //foreignrow
    }
    public static class MonsterTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public boolean IsSummoned; //bool
        @Offset(13) public int Armour; //i32
        @Offset(17) public int Evasion; //i32
        @Offset(21) public int EnergyShieldFromLife; //i32
        @Offset(25) public int DamageSpread; //i32
        @Offset(29) public ForeignRow MonsterResistancesKey; //foreignrow
        @Offset(45) public boolean IsLargeAbyssMonster; //bool
        @Offset(46) public boolean IsSmallAbyssMonster; //bool
    }
    public static class MonsterVarieties implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MonsterTypesKey; //foreignrow
        @Offset(28) public int ObjectSize; //i32
        @Offset(32) public int MinimumAttackDistance; //i32
        @Offset(36) public int MaximumAttackDistance; //i32
        @Offset(40) public List<String> ACTFiles; //string
        @Offset(56) public List<String> AOFiles; //string
        @Offset(72) public String BaseMonsterTypeIndex; //string
        @Offset(80) public List<ForeignRow> ModsKeys; //foreignrow
        @Offset(116) public int ModelSizeMultiplier; //i32
        @Offset(140) public List<ForeignRow> TagsKeys; //foreignrow
        @Offset(156) public int ExperienceMultiplier; //i32
        @Offset(188) public int CriticalStrikeChance; //i32
        @Offset(196) public List<ForeignRow> GrantedEffectsKeys; //foreignrow
        @Offset(212) public String AISFile; //string
        @Offset(220) public List<ForeignRow> ModsKeys2; //foreignrow
        @Offset(236) public String Stance; //string
        @Offset(260) public String Name; //string
        @Offset(268) public int DamageMultiplier; //i32
        @Offset(272) public int LifeMultiplier; //i32
        @Offset(276) public int AttackSpeed; //i32
        @Offset(280) public List<ForeignRow> Weapon1_ItemVisualIdentityKeys; //foreignrow
        @Offset(296) public List<ForeignRow> Weapon2_ItemVisualIdentityKeys; //foreignrow
        @Offset(312) public ForeignRow Back_ItemVisualIdentityKey; //foreignrow
        @Offset(328) public ForeignRow MainHand_ItemClassesKey; //foreignrow
        @Offset(344) public ForeignRow OffHand_ItemClassesKey; //foreignrow
        @Offset(360) public ForeignRow Helmet_ItemVisualIdentityKey; //foreignrow
        @Offset(380) public List<ForeignRow> KillSpecificMonsterCount_AchievementItemsKeys; //foreignrow
        @Offset(396) public List<ForeignRow> Special_ModsKeys; //foreignrow
        @Offset(412) public List<ForeignRow> KillRare_AchievementItemsKeys; //foreignrow
        @Offset(453) public short HASH16; //i16
        @Offset(464) public ForeignRow KillWhileOnslaughtIsActive_AchievementItemsKey; //foreignrow
        @Offset(480) public ForeignRow MonsterSegmentsKey; //foreignrow
        @Offset(496) public ForeignRow MonsterArmoursKey; //foreignrow
        @Offset(512) public ForeignRow KillWhileTalismanIsActive_AchievementItemsKey; //foreignrow
        @Offset(528) public List<ForeignRow> Part1_ModsKeys; //foreignrow
        @Offset(544) public List<ForeignRow> Part2_ModsKeys; //foreignrow
        @Offset(560) public List<ForeignRow> Endgame_ModsKeys; //foreignrow
        @Offset(636) public String SinkAnimation_AOFile; //string
        @Offset(680) public String EPKFile; //string
        @Offset(692) public ForeignRow MonsterConditionalEffectPacksKey; //foreignrow
        @Offset(739) public List<String> AddonMonsterTypeIndex; //string
        @Offset(776) public boolean BossHealthBar; //bool
    }
    public static class MonsterVarietiesArtVariations implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MouseCursorSizeSettings implements DatType{
        @Offset(0) public String Size; //string
        @Offset(8) public String Description; //string
        @Offset(16) public float Ratio; //f32
    }
    public static class MoveDaemon implements DatType{
    }
    public static class MTXSetBonus implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MultiPartAchievementAreas implements DatType{
    }
    public static class MultiPartAchievementConditions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MultiPartAchievementsKey1; //foreignrow
        @Offset(24) public ForeignRow MultiPartAchievementsKey2; //foreignrow
    }
    public static class MultiPartAchievements implements DatType{
        @Offset(0) public String Id; //string
        @Offset(12) public ForeignRow AchievementItemsKey; //foreignrow
    }
    public static class Music implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String SoundFile; //string
        @Offset(16) public String BankFile; //string
        @Offset(24) public int HASH16; //i32
        @Offset(28) public boolean IsAvailableInHideout; //bool
        @Offset(29) public String Name; //string
        @Offset(45) public List<ForeignRow> MusicCategories; //foreignrow
    }
    public static class MusicCategories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public int Order; //i32
    }
    public static class MysteryBoxes implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public String BK2File; //string
        @Offset(24) public String BoxId; //string
        @Offset(32) public String BundleId; //string
    }
    public static class NearbyMonsterConditions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> MonsterVarietiesKeys; //foreignrow
        @Offset(24) public int MonsterAmount; //i32
        @Offset(32) public boolean IsNegated; //bool
        @Offset(53) public boolean IsLessThen; //bool
        @Offset(54) public int MinimumHealthPercentage; //i32
    }
    public static class Notifications implements DatType{
        @Offset(0) public String Id; //string
        @Offset(10) public String Message; //string
    }
    public static class NPCAudio implements DatType{
        @Offset(0) public String Id; //string
        @Offset(40) public int VolumePercentage; //i32
    }
    public static class NPCConversations implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow DialogueEvent; //foreignrow
        @Offset(24) public List<ForeignRow> NPCTextAudioKeys; //foreignrow
    }
    public static class NPCDialogueStyles implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String HeaderBaseFile; //string
        @Offset(16) public String ButtomFile; //string
        @Offset(24) public List<String> BannerFiles; //string
        @Offset(40) public List<String> HeaderFiles; //string
    }
    public static class NPCFollowerVariations implements DatType{
        @Offset(0) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(16) public ForeignRow MiscAnimatedKey0; //foreignrow
        @Offset(32) public ForeignRow MiscAnimatedKey1; //foreignrow
    }
    public static class NPCMaster implements DatType{
        @Offset(0) public String Id; //string
        @Offset(10) public ForeignRow Signature_ModsKey; //foreignrow
        @Offset(27) public List<ForeignRow> SpawnWeight_TagsKeys; //foreignrow
        @Offset(43) public List<Integer> SpawnWeight_Values; //i32
        @Offset(79) public String AreaDescription; //string
        @Offset(123) public boolean HasAreaMissions; //bool
    }
    public static class NPCPortraits implements DatType{
        @Offset(0) public String Name; //string
        @Offset(8) public String PortraitFile; //string
    }
    public static class NPCs implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public String Metadata; //string
        @Offset(40) public ForeignRow NPCMasterKey; //foreignrow
        @Offset(56) public String ShortName; //string
        @Offset(68) public List<ForeignRow> NPCAudios1; //foreignrow
        @Offset(84) public List<ForeignRow> NPCAudios2; //foreignrow
        @Offset(100) public short HASH16; //i16
        @Offset(110) public ForeignRow Portrait; //foreignrow
        @Offset(126) public ForeignRow DialogueStyle; //foreignrow
        @Offset(159) public String Gender; //string
    }
    public static class NPCShop implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class NPCShopSets implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class NPCTalk implements DatType{
        @Offset(0) public ForeignRow NPCKey; //foreignrow
        @Offset(20) public String DialogueOption; //string
        @Offset(76) public String Script; //string
        @Offset(84) public ForeignRow TextAudio; //foreignrow
        @Offset(100) public ForeignRow Category; //foreignrow
        @Offset(116) public ForeignRow QuestRewardOffersKey; //foreignrow
        @Offset(132) public ForeignRow QuestFlag; //foreignrow
        @Offset(148) public List<ForeignRow> NPCTextAudioKeys; //foreignrow
        @Offset(164) public String Script2; //string
        @Offset(252) public String DialogueOption2; //string
    }
    public static class NPCTalkCategory implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class NPCTalkConsoleQuickActions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Controller; //string
    }
    public static class NPCTextAudio implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> Characters; //foreignrow
        @Offset(24) public String Text; //string
        @Offset(32) public String Mono_AudioFile; //string
        @Offset(40) public String Stereo_AudioFile; //string
        @Offset(48) public boolean HasStereo; //bool
        @Offset(50) public String Video; //string
        @Offset(70) public List<ForeignRow> NPCs; //foreignrow
    }
    public static class OnKillAchievements implements DatType{
        @Offset(0) public ForeignRow MonsterVarietiesKey; //foreignrow
        @Offset(16) public ForeignRow GameStat; //foreignrow
    }
    public static class PackFormation implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class PassiveJewelRadii implements DatType{
        @Offset(0) public String ID; //string
        @Offset(8) public int RingOuterRadius; //i32
        @Offset(12) public int RingInnerRadius; //i32
        @Offset(16) public int Radius; //i32
    }
    public static class PassiveJewelSlots implements DatType{
        @Offset(0) public ForeignRow Slot; //foreignrow
        @Offset(16) public ForeignRow ClusterJewelSize; //foreignrow
        @Offset(36) public long ReplacesSlot; //row
        @Offset(44) public ForeignRow ProxySlot; //foreignrow
        @Offset(60) public List<Integer> StartIndices; //i32
    }
    public static class PassiveJewelNodeModifyingStats implements DatType{
        @Offset(0) public ForeignRow JwelStat; //foreignrow
        @Offset(16) public ForeignRow Stat; //foreignrow
    }
    public static class PassiveSkillFilterCatagories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
    }
    public static class PassiveSkillFilterOptions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(24) public String Name; //string
    }
    public static class PassiveSkillOverrides implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public String NodeIcon; //string
        @Offset(24) public List<ForeignRow> Stats; //foreignrow
        @Offset(40) public List<Integer> StatValues; //i32
        @Offset(56) public int HASH16; //i32
        @Offset(60) public String PassiveBG; //string
        @Offset(68) public ForeignRow Effect; //foreignrow
        @Offset(84) public ForeignRow Type; //foreignrow
        @Offset(100) public ForeignRow Limit; //foreignrow
        @Offset(116) public int RequiresAdjacent; //i32
        @Offset(120) public int MaxAdjacent; //i32
        @Offset(124) public ForeignRow AllocatedPassiveSkill; //foreignrow
    }
    public static class PassiveSkillOverrideTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow CounterStat; //foreignrow
    }
    public static class PassiveOverrideLimits implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
    }
    public static class PassiveSkillMasteryGroups implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> MasteryEffects; //foreignrow
        @Offset(24) public String InactiveIcon; //string
        @Offset(32) public String ActiveIcon; //string
        @Offset(40) public String ActiveEffectImage; //string
        @Offset(49) public ForeignRow SoundEffect; //foreignrow
        @Offset(65) public ForeignRow MasteryCountStat; //foreignrow
    }
    public static class PassiveSkillMasteryEffects implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public short HASH16; //i16
        @Offset(10) public List<ForeignRow> Stats; //foreignrow
        @Offset(26) public int Stat1Value; //i32
        @Offset(30) public int Stat2Value; //i32
        @Offset(34) public int Stat3Value; //i32
        @Offset(38) public ForeignRow AchievementItem; //foreignrow
    }
    public static class PassiveSkillStatCategories implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
    }
    public static class PassiveSkillTrees implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String PassiveSkillGraph; //string
        @Offset(59) public ForeignRow UIArt; //foreignrow
    }
    public static class PassiveSkillTreeTutorial implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow CharactersKey; //foreignrow
        @Offset(40) public String ChoiceA_Description; //string
        @Offset(48) public String ChoiceB_Description; //string
        @Offset(72) public String ChoiceA_PassiveTreeURL; //string
        @Offset(80) public String ChoiceB_PassiveTreeURL; //string
    }
    public static class PassiveSkillTreeUIArt implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String GroupBackgroundSmall; //string
        @Offset(16) public String GroupBackgroundMedium; //string
        @Offset(24) public String GroupBackgroundLarge; //string
        @Offset(33) public String PassiveFrameNormal; //string
        @Offset(41) public String NotableFrameNormal; //string
        @Offset(49) public String KeystoneFrameNormal; //string
        @Offset(57) public String PassiveFrameActive; //string
        @Offset(65) public String NotableFrameActive; //string
        @Offset(73) public String KeystoneFrameActive; //string
        @Offset(81) public String PassiveFrameCanAllocate; //string
        @Offset(89) public String NotableFrameCanAllocate; //string
        @Offset(97) public String KeystoneCanAllocate; //string
        @Offset(105) public String Ornament; //string
        @Offset(113) public String GroupBackgroundSmallBlank; //string
        @Offset(121) public String GroupBackgroundMediumBlank; //string
        @Offset(129) public String GroupBackgroundLargeBlank; //string
    }
    public static class PassiveTreeExpansionJewels implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public ForeignRow PassiveTreeExpansionJewelSizesKey; //foreignrow
        @Offset(32) public int MinNodes; //i32
        @Offset(36) public int MaxNodes; //i32
        @Offset(40) public List<Integer> SmallIndices; //i32
        @Offset(56) public List<Integer> NotableIndices; //i32
        @Offset(72) public List<Integer> SocketIndices; //i32
        @Offset(88) public int TotalIndices; //i32
    }
    public static class PassiveTreeExpansionJewelSizes implements DatType{
        @Offset(0) public String Name; //string
    }
    public static class PassiveTreeExpansionSkills implements DatType{
        @Offset(0) public ForeignRow PassiveSkillsKey; //foreignrow
        @Offset(16) public ForeignRow Mastery_PassiveSkillsKey; //foreignrow
        @Offset(32) public ForeignRow TagsKey; //foreignrow
        @Offset(48) public ForeignRow PassiveTreeExpansionJewelSizesKey; //foreignrow
    }
    public static class PassiveTreeExpansionSpecialSkills implements DatType{
        @Offset(0) public ForeignRow PassiveSkillsKey; //foreignrow
        @Offset(16) public ForeignRow StatsKey; //foreignrow
    }
    public static class PCBangRewardMicros implements DatType{
    }
    public static class Pet implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(24) public int HASH16; //i32
        @Offset(28) public int HASH32; //i32
        @Offset(70) public List<ForeignRow> Skills; //foreignrow
    }
    public static class PlayerConditions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> BuffDefinitionsKeys; //foreignrow
        @Offset(25) public int BuffStacks; //i32
        @Offset(29) public ForeignRow CharactersKey; //foreignrow
        @Offset(45) public List<ForeignRow> StatsKeys; //foreignrow
        @Offset(62) public int StatValue; //i32
    }
    public static class PlayerTradeWhisperFormats implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Whisper; //string
        @Offset(16) public boolean InStash; //bool
        @Offset(17) public boolean IsPriced; //bool
    }
    public static class PreloadGroups implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class Projectiles implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<String> AOFiles; //string
        @Offset(24) public List<String> LoopAnimationIds; //string
        @Offset(40) public List<String> ImpactAnimationIds; //string
        @Offset(56) public int ProjectileSpeed; //i32
        @Offset(67) public String InheritsFrom; //string
        @Offset(101) public List<String> Stuck_AOFile; //string
        @Offset(117) public String Bounce_AOFile; //string
    }
    public static class ProjectilesArtVariations implements DatType{
        @Offset(0) public String Projectile; //string
        @Offset(8) public int Variant; //i32
    }
    public static class PVPTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class Quest implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int Act; //i32
        @Offset(12) public String Name; //string
        @Offset(20) public String Icon_DDSFile; //string
        @Offset(28) public int QuestId; //i32
        @Offset(33) public ForeignRow Type; //foreignrow
        @Offset(69) public ForeignRow TrackerGroup; //foreignrow
    }
    public static class QuestAchievements implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<Integer> QuestStates; //i32
        @Offset(40) public List<ForeignRow> AchievementItems; //foreignrow
        @Offset(56) public List<ForeignRow> NPCs; //foreignrow
    }
    public static class QuestFlags implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int HASH32; //i32
    }
    public static class QuestItems implements DatType{
        @Offset(0) public ForeignRow Item; //foreignrow
        @Offset(16) public ForeignRow TriggeredQuestFlag; //foreignrow
        @Offset(102) public String Script; //string
    }
    public static class QuestRewardOffers implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow QuestKey; //foreignrow
        @Offset(24) public ForeignRow QuestFlag; //foreignrow
        @Offset(44) public ForeignRow RewardWindowTake; //foreignrow
        @Offset(62) public ForeignRow RewardWindowTitle; //foreignrow
    }
    public static class QuestRewards implements DatType{
        @Offset(0) public ForeignRow RewardOffer; //foreignrow
        @Offset(20) public List<ForeignRow> Characters; //foreignrow
        @Offset(36) public ForeignRow Reward; //foreignrow
        @Offset(52) public int RewardLevel; //i32
        @Offset(100) public int RewardStack; //i32
    }
    public static class QuestStates implements DatType{
        @Offset(0) public ForeignRow Quest; //foreignrow
        @Offset(16) public int Order; //i32
        @Offset(20) public List<ForeignRow> FlagsPresent; //foreignrow
        @Offset(36) public List<ForeignRow> FlagsMissing; //foreignrow
        @Offset(52) public String Text; //string
        @Offset(61) public String Message; //string
        @Offset(69) public List<ForeignRow> MapPinsKeys1; //foreignrow
        @Offset(89) public List<String> MapPinsTexts; //string
        @Offset(105) public List<ForeignRow> MapPinsKeys2; //foreignrow
        @Offset(174) public ForeignRow SoundEffect; //foreignrow
    }
    public static class QuestStaticRewards implements DatType{
        @Offset(0) public ForeignRow QuestFlag; //foreignrow
        @Offset(20) public List<ForeignRow> StatsKeys; //foreignrow
        @Offset(36) public List<Integer> StatValues; //i32
        @Offset(52) public ForeignRow QuestKey; //foreignrow
        @Offset(72) public ForeignRow ClientStringsKey; //foreignrow
        @Offset(92) public List<Integer> StatValuesHardmode; //i32
        @Offset(108) public ForeignRow ClientStringHardmode; //foreignrow
    }
    public static class QuestTrackerGroup implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public ForeignRow QuestType; //foreignrow
    }
    public static class QuestType implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class Races implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> Mods; //foreignrow
    }
    public static class RaceTimes implements DatType{
        @Offset(0) public ForeignRow RacesKey; //foreignrow
        @Offset(16) public int Index; //i32
        @Offset(20) public int StartUNIXTime; //i32
        @Offset(24) public int EndUNIXTime; //i32
    }
    public static class RareMonsterLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int Life; //i32
    }
    public static class Rarity implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int MinMods; //i32
        @Offset(12) public int MaxMods; //i32
        @Offset(20) public int MaxPrefix; //i32
        @Offset(28) public int MaxSuffix; //i32
        @Offset(32) public String Color; //string
        @Offset(40) public String Text; //string
    }
    public static class Realms implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public List<String> Server; //string
        @Offset(32) public boolean IsEnabled; //bool
        @Offset(33) public List<String> Server2; //string
        @Offset(49) public String ShortName; //string
        @Offset(85) public boolean IsGammaRealm; //bool
        @Offset(86) public List<String> SpeedtestUrl; //string
    }
    public static class RecipeUnlockDisplay implements DatType{
        @Offset(0) public int RecipeId; //i32
        @Offset(4) public String Description; //string
        @Offset(12) public List<ForeignRow> CraftingItemClassCategoriesKeys; //foreignrow
        @Offset(28) public String UnlockDescription; //string
        @Offset(36) public int Rank; //i32
        @Offset(40) public ForeignRow UnlockArea; //foreignrow
    }
    public static class RecipeUnlockObjects implements DatType{
        @Offset(0) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(16) public String InheritsFrom; //string
        @Offset(24) public int RecipeId; //i32
    }
    public static class ReminderText implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(16) public String TextHardmode; //string
    }
    public static class Rulesets implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class RunicCircles implements DatType{
    }
    public static class SalvageBoxes implements DatType{
        @Offset(0) public ForeignRow BaseItemType; //foreignrow
        @Offset(16) public String Id; //string
    }
    public static class SessionQuestFlags implements DatType{
        @Offset(0) public ForeignRow QuestFlag; //foreignrow
    }
    public static class ShieldTypes implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public int Block; //i32
    }
    public static class ShopTag implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public boolean IsCategory; //bool
        @Offset(17) public long Category; //row
        @Offset(25) public List<ForeignRow> SkillGem; //foreignrow
    }
    public static class SigilDisplay implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Active_StatsKey; //foreignrow
        @Offset(24) public ForeignRow Inactive_StatsKey; //foreignrow
        @Offset(40) public String DDSFile; //string
        @Offset(48) public String Inactive_ArtFile; //string
        @Offset(56) public String Active_ArtFile; //string
        @Offset(64) public String Frame_ArtFile; //string
    }
    public static class SingleGroundLaser implements DatType{
        @Offset(0) public int Id; //i32
    }
    public static class SkillArtVariations implements DatType{
        @Offset(0) public String Id; //string
        @Offset(72) public List<String> Variants; //string
    }
    public static class SkillGemInfo implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
        @Offset(16) public String VideoURL1; //string
        @Offset(24) public ForeignRow SkillGemsKey; //foreignrow
        @Offset(40) public String VideoURL2; //string
        @Offset(48) public List<ForeignRow> CharactersKeys; //foreignrow
    }
    public static class SkillGems implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public int StrengthRequirementPercent; //i32
        @Offset(20) public int DexterityRequirementPercent; //i32
        @Offset(24) public int IntelligenceRequirementPercent; //i32
        @Offset(28) public ForeignRow VaalVariant_BaseItemTypesKey; //foreignrow
        @Offset(44) public boolean IsVaalVariant; //bool
        @Offset(45) public ForeignRow MinionGlobalSkillLevelStat; //foreignrow
        @Offset(61) public int GemType; //i32
        @Offset(91) public ForeignRow ItemExperienceType; //foreignrow
        @Offset(107) public List<ForeignRow> CraftingTypes; //foreignrow
        @Offset(140) public List<ForeignRow> GemEffects; //foreignrow
        @Offset(157) public String TutorialVideo; //string
        @Offset(165) public String UI_Image; //string
        @Offset(177) public int CraftingLevel; //i32
    }
    public static class SkillMineVariations implements DatType{
        @Offset(0) public int SkillMinesKey; //i32
        @Offset(8) public ForeignRow MiscObject; //foreignrow
    }
    public static class SkillMorphDisplay implements DatType{
        @Offset(32) public List<String> DDSFiles; //string
    }
    public static class SkillSurgeEffects implements DatType{
        @Offset(0) public ForeignRow GrantedEffectsKey; //foreignrow
        @Offset(27) public ForeignRow MiscAnimated; //foreignrow
    }
    public static class SkillTotemVariations implements DatType{
        @Offset(0) public int SkillTotemsKey; //enumrow
        @Offset(4) public int TotemSkinId; //i32
        @Offset(8) public ForeignRow MonsterVarietiesKey; //foreignrow
    }
    public static class SkillTrapVariations implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public String Metadata; //string
        @Offset(12) public ForeignRow MiscAnimated; //foreignrow
    }
    public static class SocketNotches implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
        @Offset(16) public String RedSocketImage; //string
        @Offset(24) public String BlueSocketImage; //string
        @Offset(32) public String GreenSocketImage; //string
    }
    public static class SoundEffects implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String SoundFile; //string
        @Offset(16) public String SoundFile_2D; //string
    }
    public static class SpawnAdditionalChestsOrClusters implements DatType{
        @Offset(0) public ForeignRow StatsKey; //foreignrow
        @Offset(16) public ForeignRow ChestsKey; //foreignrow
        @Offset(32) public ForeignRow ChestClustersKey; //foreignrow
    }
    public static class SpawnObject implements DatType{
    }
    public static class SpecialRooms implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String ARMFile; //string
    }
    public static class SpecialTiles implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String TDTFile; //string
    }
    public static class SpectreOverrides implements DatType{
        @Offset(0) public ForeignRow Monster; //foreignrow
        @Offset(16) public ForeignRow Spectre; //foreignrow
    }
    public static class StartingPassiveSkills implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> PassiveSkills; //foreignrow
    }
    public static class StashTabAffinities implements DatType{
        @Offset(0) public int SpecializedStash; //enumrow
        @Offset(4) public String Name; //string
        @Offset(12) public List<Integer> ShowInStashes; //enumrow
    }
    public static class StashType implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int StashId; //enumrow
        @Offset(12) public String Id2; //string
        @Offset(32) public String Icon; //string
    }
    public static class StatDescriptionFunctions implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String TranslationId; //string
    }
    public static class StatsAffectingGeneration implements DatType{
        @Offset(0) public ForeignRow Stat; //foreignrow
        @Offset(16) public int StatValue; //i32
    }
    public static class Stats implements DatType{
        @Offset(0) public String Id; //string
        @Offset(9) public boolean IsLocal; //bool
        @Offset(10) public boolean IsWeaponLocal; //bool
        @Offset(11) public int Semantics; //enumrow
        @Offset(15) public String Text; //string
        @Offset(24) public boolean IsVirtual; //bool
        @Offset(25) public long MainHandAlias_StatsKey; //row
        @Offset(33) public long OffHandAlias_StatsKey; //row
        @Offset(42) public int HASH32; //i32
        @Offset(46) public List<String> BelongsActiveSkillsKey; //string
        @Offset(62) public ForeignRow Category; //foreignrow
        @Offset(80) public boolean IsScalable; //bool
        @Offset(81) public List<ForeignRow> ContextFlags; //foreignrow
    }
    public static class SuicideExplosion implements DatType{
        @Offset(0) public int Id; //i32
    }
    public static class SummonedSpecificBarrels implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Chest; //foreignrow
    }
    public static class SummonedSpecificMonsters implements DatType{
        @Offset(0) public int Id; //i32
        @Offset(4) public ForeignRow MonsterVarietiesKey; //foreignrow
    }
    public static class SummonedSpecificMonstersOnDeath implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow MonsterVarietiesKey; //foreignrow
    }
    public static class SurgeEffects implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class SurgeTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public List<ForeignRow> SurgeEffects; //foreignrow
        @Offset(24) public int IntId; //i32
    }
    public static class TableCharge implements DatType{
    }
    public static class TableMonsterSpawners implements DatType{
        @Offset(0) public String Metadata; //string
        @Offset(8) public int AreaLevel; //i32
        @Offset(12) public List<ForeignRow> SpawnsMonsters; //foreignrow
        @Offset(100) public String Script1; //string
        @Offset(110) public String Script2; //string
    }
    public static class Tags implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int HASH32; //i32
        @Offset(12) public String DisplayString; //string
        @Offset(36) public String UiHints; //string
    }
    public static class TalkingPetAudioEvents implements DatType{
        @Offset(0) public String Event; //string
    }
    public static class TalkingPetNPCAudio implements DatType{
    }
    public static class TalkingPets implements DatType{
    }
    public static class TencentAutoLootPetCurrencies implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
    }
    public static class TencentAutoLootPetCurrenciesExcludable implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
    }
    public static class TerrainPlugins implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class Tips implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(16) public String TextXBox; //string
    }
    public static class Topologies implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String DGRFile; //string
    }
    public static class TradeMarketCategory implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public int StyleFlag; //enumrow
        @Offset(20) public ForeignRow Group; //foreignrow
        @Offset(53) public boolean IsDisabled; //bool
    }
    public static class TradeMarketCategoryGroups implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
    }
    public static class TradeMarketCategoryListAllClass implements DatType{
        @Offset(0) public ForeignRow TradeCategory; //foreignrow
        @Offset(16) public ForeignRow ItemClass; //foreignrow
    }
    public static class TradeMarketIndexItemAs implements DatType{
        @Offset(0) public ForeignRow Item; //foreignrow
        @Offset(16) public ForeignRow IndexAs; //foreignrow
    }
    public static class TriggerBeam implements DatType{
    }
    public static class TriggerSpawners implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class Tutorial implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String UIFile; //string
        @Offset(16) public ForeignRow ClientString; //foreignrow
        @Offset(32) public boolean IsEnabled; //bool
    }
    public static class UITalkText implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int UITalkCategoriesKey; //enumrow
        @Offset(12) public String OGGFile; //string
        @Offset(20) public String Text; //string
        @Offset(29) public ForeignRow NPCTextAudio; //foreignrow
    }
    public static class UniqueChests implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow WordsKey; //foreignrow
        @Offset(24) public ForeignRow FlavourTextKey; //foreignrow
        @Offset(40) public int MinLevel; //i32
        @Offset(44) public List<ForeignRow> ModsKeys; //foreignrow
        @Offset(60) public int SpawnWeight; //i32
        @Offset(80) public String AOFile; //string
        @Offset(105) public ForeignRow AppearanceChestsKey; //foreignrow
        @Offset(121) public ForeignRow ChestsKey; //foreignrow
    }
    public static class UniqueJewelLimits implements DatType{
        @Offset(0) public ForeignRow JewelName; //foreignrow
        @Offset(16) public int Limit; //i32
    }
    public static class UniqueMaps implements DatType{
        @Offset(0) public ForeignRow ItemVisualIdentityKey; //foreignrow
        @Offset(16) public ForeignRow WorldAreasKey; //foreignrow
        @Offset(32) public ForeignRow WordsKey; //foreignrow
        @Offset(48) public ForeignRow FlavourTextKey; //foreignrow
        @Offset(64) public boolean HasGuildCharacter; //bool
        @Offset(65) public String GuildCharacter; //string
        @Offset(73) public String Name; //string
        @Offset(81) public boolean IsDropDisabled; //bool
    }
    public static class UniqueStashLayout implements DatType{
        @Offset(0) public ForeignRow WordsKey; //foreignrow
        @Offset(16) public ForeignRow ItemVisualIdentityKey; //foreignrow
        @Offset(32) public ForeignRow UniqueStashTypesKey; //foreignrow
        @Offset(56) public int OverrideWidth; //i32
        @Offset(60) public int OverrideHeight; //i32
        @Offset(64) public boolean ShowIfEmptyChallengeLeague; //bool
        @Offset(65) public boolean ShowIfEmptyStandard; //bool
        @Offset(66) public long RenamedVersion; //row
        @Offset(74) public long BaseVersion; //row
        @Offset(82) public boolean IsAlternateArt; //bool
    }
    public static class UniqueStashTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int Order; //i32
        @Offset(12) public int Width; //i32
        @Offset(16) public int Height; //i32
        @Offset(20) public int TotalCount; //i32
        @Offset(28) public String Name; //string
        @Offset(36) public int StandardCount; //i32
        @Offset(40) public String Image; //string
        @Offset(48) public int ChallengeLeagueCount; //i32
        @Offset(52) public boolean IsDisabled; //bool
    }
    public static class VirtualStatContextFlags implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class VoteState implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
    }
    public static class VoteType implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
        @Offset(16) public String AcceptText; //string
        @Offset(24) public String RejectText; //string
    }
    public static class WeaponClasses implements DatType{
        @Offset(0) public ForeignRow ItemClass; //foreignrow
        @Offset(16) public int RangeMax; //i32
    }
    public static class WeaponImpactSoundData implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class WeaponTypes implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public int Critical; //i32
        @Offset(36) public int Speed; //i32
        @Offset(40) public int DamageMin; //i32
        @Offset(44) public int DamageMax; //i32
        @Offset(48) public int RangeMax; //i32
    }
    public static class WindowCursors implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String CursorDefault; //string
        @Offset(16) public String CursorClick; //string
        @Offset(32) public String CursorHover; //string
        @Offset(40) public String Description; //string
        @Offset(48) public boolean IsEnabled; //bool
    }
    public static class Words implements DatType{
        @Offset(0) public int Wordlist; //enumrow
        @Offset(4) public String Text; //string
        @Offset(12) public List<ForeignRow> SpawnWeight_Tags; //foreignrow
        @Offset(28) public List<Integer> SpawnWeight_Values; //i32
        @Offset(48) public String Text2; //string
        @Offset(56) public String Inflection; //string
    }
    public static class WorldAreas implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public int Act; //i32
        @Offset(20) public boolean IsTown; //bool
        @Offset(21) public boolean HasWaypoint; //bool
        @Offset(22) public List<Long> Connections_WorldAreasKeys; //row
        @Offset(38) public int AreaLevel; //i32
        @Offset(42) public short HASH16; //i16
        @Offset(52) public String LoadingScreen_DDSFile; //string
        @Offset(96) public List<ForeignRow> TopologiesKeys; //foreignrow
        @Offset(112) public long ParentTown_WorldAreasKey; //row
        @Offset(156) public List<ForeignRow> Bosses_MonsterVarietiesKeys; //foreignrow
        @Offset(172) public List<ForeignRow> Monsters_MonsterVarietiesKeys; //foreignrow
        @Offset(188) public List<ForeignRow> SpawnWeight_TagsKeys; //foreignrow
        @Offset(204) public List<Integer> SpawnWeight_Values; //i32
        @Offset(220) public boolean IsMapArea; //bool
        @Offset(221) public List<ForeignRow> FullClear_AchievementItemsKeys; //foreignrow
        @Offset(253) public ForeignRow AchievementItemsKey; //foreignrow
        @Offset(269) public List<ForeignRow> ModsKeys; //foreignrow
        @Offset(289) public List<Long> VaalArea; //row
        @Offset(306) public int MaxLevel; //i32
        @Offset(310) public List<ForeignRow> AreaTypeTags; //foreignrow
        @Offset(326) public boolean IsHideout; //bool
        @Offset(327) public String Inflection; //string
        @Offset(343) public List<ForeignRow> Tags; //foreignrow
        @Offset(359) public boolean IsVaalArea; //bool
        @Offset(360) public boolean IsLabyrinthAirlock; //bool
        @Offset(361) public boolean IsLabyrinthArea; //bool
        @Offset(362) public ForeignRow TwinnedFullClear_AchievementItemsKey; //foreignrow
        @Offset(378) public ForeignRow Enter_AchievementItemsKey; //foreignrow
        @Offset(394) public String TSIFile; //string
        @Offset(418) public List<ForeignRow> WaypointActivation_AchievementItemsKeys; //foreignrow
        @Offset(434) public boolean IsUniqueMapArea; //bool
        @Offset(435) public boolean IsLabyrinthBossArea; //bool
        @Offset(436) public ForeignRow FirstEntry_NPCTextAudioKey; //foreignrow
        @Offset(452) public ForeignRow FirstEntry_SoundEffectsKey; //foreignrow
        @Offset(468) public String FirstEntry_NPCsKey; //string
        @Offset(480) public ForeignRow EnvironmentsKey; //foreignrow
        @Offset(546) public ForeignRow LeagueChance1; //foreignrow
        @Offset(566) public ForeignRow LeagueChance2; //foreignrow
        @Offset(598) public ForeignRow Ruleset; //foreignrow
        @Offset(618) public ForeignRow QuestFlag; //foreignrow
    }
    public static class WorldAreaLeagueChances implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class WorldPopupIconTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class AbyssBossLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int Life; //i32
    }
    public static class ActionTypes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class ActiveSettings implements DatType{
    }
    public static class AdditionalMonstersFromMapStats implements DatType{
    }
    public static class AlternateTreeArt implements DatType{
        @Offset(16) public String Circle1; //string
        @Offset(24) public String Circle2; //string
        @Offset(32) public String Glow; //string
    }
    public static class AnimateWeaponUniques implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow ItemVisualIdentity; //foreignrow
        @Offset(24) public ForeignRow ItemClass; //foreignrow
    }
    public static class AtlasMemoryLine implements DatType{
        @Offset(0) public String League; //string
        @Offset(8) public String League2; //string
        @Offset(16) public String StartPointArt; //string
        @Offset(24) public String MidPointArt; //string
        @Offset(32) public String EndPointArt; //string
        @Offset(40) public String PathArt; //string
    }
    public static class BattlePasses implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public int LeagueCategory; //enumrow
        @Offset(12) public String International_BK2File; //string
        @Offset(20) public String China_BK2File; //string
        @Offset(28) public int MapCompletionCount; //i32
        @Offset(33) public String Id2; //string
    }
    public static class BattlePassRewards implements DatType{
        @Offset(0) public ForeignRow BattlePass; //foreignrow
        @Offset(16) public int RewardTier; //i32
        @Offset(29) public String Id; //string
        @Offset(37) public List<ForeignRow> RewardedMTX; //foreignrow
        @Offset(57) public String RewardDescription; //string
        @Offset(82) public String RewardTitle; //string
    }
    public static class BattlePassTracks implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
    }
    public static class BreachArtVariations implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class Breachstones implements DatType{
        @Offset(0) public ForeignRow BaseType; //foreignrow
        @Offset(16) public int MapTierEquivalent; //i32
        @Offset(24) public ForeignRow UpgradesTo; //foreignrow
        @Offset(40) public ForeignRow UpgradeCurrency; //foreignrow
    }
    public static class ConditionalAchievements implements DatType{
    }
    public static class CorpseSinkVariations implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String AOFile; //string
        @Offset(16) public String EPKFile; //string
        @Offset(24) public List<String> AOFiles; //string
    }
    public static class CosmeticsEquipPanelMode implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class CraftingBenchSpecificOptionId implements DatType{
    }
    public static class CurrencyUseEffects implements DatType{
        @Offset(20) public String BK2File; //string
        @Offset(28) public String SoundFile; //string
        @Offset(37) public String BK2File2; //string
    }
    public static class DamageEffectVariations implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class DamageWhenHitEffects implements DatType{
    }
    public static class DelveRobotVariations implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String AOFile; //string
    }
    public static class DivinationWindowVisuals implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String WindowArt; //string
        @Offset(16) public String ButtonArt; //string
    }
    public static class EntityInfobarStyle implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String FrameLeft; //string
        @Offset(16) public String FrameMiddle; //string
        @Offset(24) public String FrameRepeat; //string
        @Offset(32) public String FrameRight; //string
        @Offset(40) public String EntityLeft; //string
        @Offset(48) public String EntityMiddle; //string
        @Offset(56) public String EntityRepeat; //string
        @Offset(64) public String EntityRight; //string
        @Offset(72) public String EntityStats; //string
        @Offset(80) public String EntityStatsSmall; //string
        @Offset(96) public String LifeBar; //string
        @Offset(104) public String LifeBarLeft; //string
        @Offset(112) public String LifeBarMiddle; //string
        @Offset(120) public String LifeBarRepeat; //string
        @Offset(128) public String LifeBarRight; //string
    }
    public static class FlaskStashBaseTypeOrdering implements DatType{
        @Offset(0) public ForeignRow Flask; //foreignrow
        @Offset(16) public int Order; //i32
    }
    public static class GameObjectTasksFromStats implements DatType{
    }
    public static class GamepadButtonCombination implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Button1; //foreignrow
        @Offset(24) public ForeignRow Button2; //foreignrow
    }
    public static class GamepadThumbstick implements DatType{
    }
    public static class GemEffects implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Name; //string
        @Offset(16) public ForeignRow GrantedEffect; //foreignrow
        @Offset(32) public String SupportText; //string
        @Offset(40) public String SupportName; //string
        @Offset(48) public List<ForeignRow> GemTags; //foreignrow
        @Offset(64) public ForeignRow Consumed_ModsKey; //foreignrow
        @Offset(80) public int ItemColor; //i32
        @Offset(100) public List<ForeignRow> AdditionalGrantedEffects; //foreignrow
    }
    public static class GoldActScaling implements DatType{
        @Offset(0) public String Act; //string
    }
    public static class GoldConstants implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class HardModeExtraContentChances implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class HardModeExtraContentChancesPerMapTier implements DatType{
    }
    public static class HarvestCraftCostScalingByBasetype implements DatType{
    }
    public static class HideoutCraftingBenchDoodads implements DatType{
    }
    public static class HideoutCraftingBenchInterfaceVisuals implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class HideoutStashDoodads implements DatType{
    }
    public static class HideoutWaypointDoodads implements DatType{
    }
    public static class HudEnergyShieldVisuals implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class HudLifeVisuals implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class HudVisualsFromStat implements DatType{
    }
    public static class InfluenceAmbushVariations implements DatType{
    }
    public static class ItemClassPickupEffect implements DatType{
    }
    public static class ItemClassVisualReplacement implements DatType{
    }
    public static class ItemPickupEffect implements DatType{
    }
    public static class ItemVisualReplacement implements DatType{
    }
    public static class LegacyAtlasInfluenceOutcomes implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MapStashSubstashGroup implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Label; //string
        @Offset(16) public String Icon; //string
        @Offset(40) public String Description; //string
    }
    public static class MemoryLineType implements DatType{
        @Offset(0) public String Id; //string
        @Offset(88) public String Suffix; //string
    }
    public static class MicrotransactionAppliedInventoryItemArtVariations implements DatType{
        @Offset(16) public List<String> DDSFiles; //string
    }
    public static class MicrotransactionChargeVariations implements DatType{
    }
    public static class MicrotransactionConditionalApparitionEvents implements DatType{
    }
    public static class MicrotransactionConditionalApparitions implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MicrotransactionEquippedIconVariations implements DatType{
        @Offset(16) public List<String> DDSFiles; //string
    }
    public static class MicrotransactionJewelVariations implements DatType{
    }
    public static class MicrotransactionObjectEffects implements DatType{
        @Offset(0) public String Id; //string
        @Offset(24) public String Script; //string
    }
    public static class MicrotransactionOnKillBeams implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MicrotransactionOnKillConditions implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MicrotransactionOnKillEffects implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MicrotransactionSkillGemEffectSlotTypes implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Type; //string
    }
    public static class MicrotransactionSlotAdditionalDefaultOptions implements DatType{
    }
    public static class MinionType implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class MonsterStatueOverrides implements DatType{
    }
    public static class PassiveJewelArt implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public String SocketedImage; //string
        @Offset(24) public String SocketedImageLarge; //string
    }
    public static class PortalAudio implements DatType{
    }
    public static class PortalAudioEvents implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class PreloadFromStats implements DatType{
    }
    public static class RelicInventoryLayout implements DatType{
        @Offset(12) public String Requirement; //string
    }
    public static class RelicItemEffectVariations implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Description; //string
        @Offset(16) public String DDSFile; //string
    }
    public static class ShrineVisualArtVariations implements DatType{
    }
    public static class SkillGemLevelUpEffects implements DatType{
        @Offset(0) public String Id; //string
    }
    public static class SkillWeaponEffects implements DatType{
    }
    public static class StatisticTrackingMicrotransactions implements DatType{
    }
    public static class StatisticTrackingMicrotransactionsStatistics implements DatType{
    }
    public static class StatsFromSkillStats implements DatType{
    }
    public static class StatVisuals implements DatType{
        @Offset(16) public List<String> EPKFiles; //string
    }
    public static class SummonedSpecificMonstersOnDeathStats implements DatType{
    }
    public static class TieredMicrotransactions implements DatType{
        @Offset(0) public ForeignRow MTX; //foreignrow
        @Offset(16) public List<Integer> TierThresholds; //i32
        @Offset(80) public int TierCount; //i32
    }
    public static class TieredMicrotransactionsVisuals implements DatType{
        @Offset(0) public ForeignRow MTX; //foreignrow
        @Offset(16) public int Tier; //i32
        @Offset(20) public String Description; //string
        @Offset(28) public String DDSFile; //string
    }
    public static class TormentedSpiritLifeScalingPerLevel implements DatType{
        @Offset(0) public int Level; //i32
        @Offset(4) public int Life; //i32
    }
    public static class TradeMarketImplicitModDisplay implements DatType{
    }
    public static class TryTheNewLeagueVersions implements DatType{
        @Offset(0) public String League; //string
        @Offset(8) public String Logo; //string
    }
    public static class UltimatumLifescalingPerLevel implements DatType{
    }
    public static class TypeTags implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow Tag; //foreignrow
        @Offset(24) public String Name; //string
        @Offset(32) public String Icon; //string
    }
    public static class AtlasPassiveSkillSubTrees implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String UI_Image; //string
        @Offset(16) public String UI_Background; //string
        @Offset(24) public int IllustrationX; //i32
        @Offset(28) public int IllustrationY; //i32
        @Offset(32) public int CounterX; //i32
        @Offset(36) public int CounterY; //i32
    }
    public static class Chanceableitemclasses implements DatType{
        @Offset(0) public ForeignRow ItemClass; //foreignrow
    }
    public static class CharacterCreationButton implements DatType{
    }
    public static class CharacterCreationDialogue implements DatType{
    }
    public static class EndgameCorruptionMods implements DatType{
    }
    public static class GoldModPrices implements DatType{
        @Offset(0) public ForeignRow Mod; //foreignrow
        @Offset(16) public int Value; //i32
        @Offset(32) public List<ForeignRow> Tags; //foreignrow
        @Offset(48) public List<Integer> SpawnWeight; //i32
        @Offset(64) public ForeignRow CraftableType; //foreignrow
    }
    public static class CraftableModTypes implements DatType{
        @Offset(0) public ForeignRow ModType; //foreignrow
        @Offset(16) public short HASH16; //u16
    }
    public static class ItemInherentSkills implements DatType{
        @Offset(0) public ForeignRow BaseItemTypes; //foreignrow
        @Offset(16) public List<ForeignRow> SkillsGranted; //foreignrow
        @Offset(32) public boolean IsWeapon; //bool
    }
    public static class ItemSpirit implements DatType{
        @Offset(0) public ForeignRow BaseItemTypes; //foreignrow
        @Offset(16) public int SpiritGranted; //i32
    }
    public static class KeywordPopups implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Term; //string
        @Offset(16) public String Definition; //string
    }
    public static class PassiveSkills implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Icon_DDSFile; //string
        @Offset(16) public List<ForeignRow> Stats; //foreignrow
        @Offset(32) public int Stat1Value; //i32
        @Offset(36) public int Stat2Value; //i32
        @Offset(40) public int Stat3Value; //i32
        @Offset(44) public int Stat4Value; //i32
        @Offset(48) public short PassiveSkillGraphId; //u16
        @Offset(50) public String Name; //string
        @Offset(58) public List<ForeignRow> Characters; //foreignrow
        @Offset(74) public boolean IsKeystone; //bool
        @Offset(75) public boolean IsNotable; //bool
        @Offset(76) public String FlavourText; //string
        @Offset(84) public boolean IsJustIcon; //bool
        @Offset(85) public ForeignRow AchievementItem; //foreignrow
        @Offset(101) public boolean IsJewelSocket; //bool
        @Offset(102) public ForeignRow AscendancyKey; //foreignrow
        @Offset(118) public boolean IsAscendancyStartingNode; //bool
        @Offset(119) public List<ForeignRow> ReminderStrings; //foreignrow
        @Offset(135) public int SkillPointsGranted; //i32
        @Offset(139) public boolean IsMultipleChoice; //bool
        @Offset(140) public boolean IsMultipleChoiceOption; //bool
        @Offset(141) public int Stat5Value; //i32
        @Offset(145) public List<ForeignRow> PassiveSkillBuffs; //foreignrow
        @Offset(161) public boolean IsAnointmentOnly; //bool
        @Offset(166) public boolean IsExpansion; //bool
        @Offset(167) public boolean IsProxyPassive; //bool
        @Offset(168) public int SkillType; //enumrow
        @Offset(172) public ForeignRow MasteryGroup; //foreignrow
        @Offset(188) public ForeignRow Group; //foreignrow
        @Offset(204) public ForeignRow SoundEffect; //foreignrow
        @Offset(286) public ForeignRow KeystoneId; //foreignrow
        @Offset(303) public boolean IsAttribute; //bool
        @Offset(304) public ForeignRow AtlasSubTree; //foreignrow
        @Offset(320) public boolean IsRootOfAtlasTree; //bool
        @Offset(321) public ForeignRow GrantedSkill; //foreignrow
        @Offset(337) public int WeaponPointsGranted; //i32
    }
    public static class SkillGemSupports implements DatType{
        @Offset(0) public ForeignRow SkillGemsKey; //foreignrow
        @Offset(16) public List<ForeignRow> Supports; //foreignrow
    }
    public static class SkillGemsForUniqueStat implements DatType{
        @Offset(0) public int id; //i32
        @Offset(4) public List<ForeignRow> gems; //foreignrow
    }
    public static class SupportGems implements DatType{
        @Offset(0) public ForeignRow SkillGemsKey; //foreignrow
        @Offset(20) public String Icon; //string
    }
    public static class UncutGemAdditionalTiers implements DatType{
        @Offset(0) public ForeignRow BaseItemType; //foreignrow
        @Offset(16) public int AreaLevel; //i32
        @Offset(20) public int Tier; //i32
        @Offset(24) public int Odds; //i32
    }
    public static class UncutGemTiers implements DatType{
        @Offset(0) public ForeignRow BaseItemType; //foreignrow
        @Offset(16) public int Tier; //i32
        @Offset(20) public int AreaLevel; //i32
    }
    public static class ClassPassiveSkillOverrides implements DatType{
        @Offset(0) public ForeignRow CharacterToOverrideFor; //foreignrow
        @Offset(16) public ForeignRow SkillToOverride; //foreignrow
        @Offset(32) public ForeignRow Override; //foreignrow
    }
    public static class AttributeRequirements implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public int ReqStr; //i32
        @Offset(20) public int ReqInt; //i32
        @Offset(24) public int ReqDex; //i32
    }
    public static class BeltTypes implements DatType{
        @Offset(0) public ForeignRow BaseItem; //foreignrow
        @Offset(16) public int CharmSlots; //i32
    }
    public static class CharacterMeleeSkills implements DatType{
        @Offset(32) public ForeignRow SkillGemsKey; //foreignrow
    }
    public static class GrantedEffectLabels implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public String Text; //string
    }
    public static class SkillCraftingData implements DatType{
        @Offset(0) public String Id; //string
        @Offset(8) public ForeignRow CharactersKey; //foreignrow
        @Offset(24) public String ListBackground; //string
        @Offset(32) public String Icon; //string
        @Offset(40) public String ConsoleIcon; //string
        @Offset(48) public String Name; //string
    }
    public static class SoulCores implements DatType{
        @Offset(0) public ForeignRow BaseItemTypesKey; //foreignrow
        @Offset(16) public List<ForeignRow> StatsKeysWeapon; //foreignrow
        @Offset(32) public List<Integer> StatsValuesWeapon; //i32
        @Offset(48) public List<ForeignRow> StatsKeysArmour; //foreignrow
        @Offset(64) public List<Integer> StatsValuesArmour; //i32
    }
    public static class UtilityFlaskBuffs implements DatType{
        @Offset(0) public ForeignRow BuffDefinitionsKey; //foreignrow
        @Offset(16) public List<Integer> StatValues; //i32
        @Offset(32) public List<Integer> StatValues2; //i32
    }
    public static final List<Class<? extends DatType>> TYPES = List.of(
        AbyssBossLifeScalingPerLevel.class,
        AbyssObjects.class,
        AchievementItemRewards.class,
        AchievementItems.class,
        AchievementSetRewards.class,
        AchievementSetsDisplay.class,
        Achievements.class,
        ActionTypes.class,
        ActiveSettings.class,
        ActiveSkillType.class,
        ActiveSkills.class,
        Acts.class,
        AddBuffToTargetVarieties.class,
        AdditionalLifeScaling.class,
        AdditionalMonsterPacksFromStats.class,
        AdditionalMonstersFromMapStats.class,
        AdvancedSkillsTutorial.class,
        AegisVariations.class,
        AfflictionBalancePerLevel.class,
        AfflictionDustedMonsterCurrencyDropWeightingByItemRarity.class,
        AfflictionEndgameWaveMods.class,
        AfflictionFixedMods.class,
        AfflictionRandomModCategories.class,
        AfflictionRewardMapMods.class,
        AfflictionRewardTypeVisuals.class,
        AfflictionSplitDemons.class,
        AfflictionStartDialogue.class,
        AlternatePassiveAdditions.class,
        AlternatePassiveSkills.class,
        AlternateQualityTypes.class,
        AlternateSkillTargetingBehaviours.class,
        AlternateTreeArt.class,
        AlternateTreeVersions.class,
        AncestralEmbraceVariations.class,
        AncestralLifeScalingPerLevel.class,
        AncestralTrialChieftains.class,
        AncestralTrialDialogue.class,
        AncestralTrialDialogueEvents.class,
        AncestralTrialFieldItems.class,
        AncestralTrialItems.class,
        AncestralTrialMonsters.class,
        AncestralTrialPositionType.class,
        AncestralTrialShopSlotEntries.class,
        AncestralTrialTribeOpinions.class,
        AncestralTrialTribes.class,
        AncestralTrialUnitPositions.class,
        AncestralTrialUnits.class,
        AnimateWeaponUniques.class,
        AnimatedObjectFlags.class,
        Animation.class,
        ApplyDamageFunctions.class,
        ArchetypeRewards.class,
        Archetypes.class,
        ArchitectLifeScalingPerLevel.class,
        ArchnemesisMetaRewards.class,
        ArchnemesisModComboAchievements.class,
        ArchnemesisModVisuals.class,
        ArchnemesisMods.class,
        ArchnemesisRecipes.class,
        AreaInfluenceDoodads.class,
        AreaTransitionAnimationTypes.class,
        AreaTransitionAnimations.class,
        AreaTransitionInfo.class,
        ArmourTypes.class,
        Ascendancy.class,
        AtlasExileBossArenas.class,
        AtlasExileInfluence.class,
        AtlasExiles.class,
        AtlasFavouredMapSlots.class,
        AtlasFog.class,
        AtlasInfluenceData.class,
        AtlasInfluenceOutcomes.class,
        AtlasInfluenceSets.class,
        AtlasMemoryLine.class,
        AtlasMods.class,
        AtlasNode.class,
        AtlasNodeDefinition.class,
        AtlasPassiveSkillSubTrees.class,
        AtlasPassiveSkillTreeGroupType.class,
        AtlasPositions.class,
        AtlasPrimordialAltarChoiceTypes.class,
        AtlasPrimordialAltarChoices.class,
        AtlasPrimordialBossInfluence.class,
        AtlasPrimordialBossOptions.class,
        AtlasPrimordialBosses.class,
        AtlasTrees.class,
        AtlasUpgradesInventoryLayout.class,
        AttributeRequirements.class,
        AwardDisplay.class,
        AzmeriCounterQuests.class,
        AzmeriFeatureRooms.class,
        AzmeriLifeScalingPerLevel.class,
        AzmeriWoodsDustType.class,
        BackendErrors.class,
        BaseItemTypes.class,
        BattlePassRewards.class,
        BattlePassTracks.class,
        BattlePasses.class,
        BeltTypes.class,
        BestiaryCapturableMonsters.class,
        BestiaryEncounters.class,
        BestiaryFamilies.class,
        BestiaryGenus.class,
        BestiaryGroups.class,
        BestiaryNets.class,
        BestiaryRecipeCategories.class,
        BestiaryRecipeComponent.class,
        BestiaryRecipes.class,
        BetrayalChoiceActions.class,
        BetrayalChoices.class,
        BetrayalDialogue.class,
        BetrayalForts.class,
        BetrayalJobs.class,
        BetrayalRanks.class,
        BetrayalRelationshipState.class,
        BetrayalTargetJobAchievements.class,
        BetrayalTargetLifeScalingPerLevel.class,
        BetrayalTargets.class,
        BetrayalTraitorRewards.class,
        BetrayalUpgrades.class,
        BetrayalWallLifeScalingPerLevel.class,
        BeyondFactions.class,
        BindableVirtualKeys.class,
        BlightBalancePerLevel.class,
        BlightBossLifeScalingPerLevel.class,
        BlightChestTypes.class,
        BlightCraftingItems.class,
        BlightCraftingRecipes.class,
        BlightCraftingResults.class,
        BlightCraftingTypes.class,
        BlightCraftingUniques.class,
        BlightEncounterTypes.class,
        BlightEncounterWaves.class,
        BlightRewardTypes.class,
        BlightStashTabLayout.class,
        BlightTopologies.class,
        BlightTopologyNodes.class,
        BlightTowerAuras.class,
        BlightTowers.class,
        BlightTowersPerLevel.class,
        BlightedSporeAuras.class,
        BloodTypes.class,
        BreachArtVariations.class,
        BreachBossLifeScalingPerLevel.class,
        BreachElement.class,
        Breachstones.class,
        BuffDefinitions.class,
        BuffTemplates.class,
        BuffVisualOrbArt.class,
        BuffVisualOrbTypes.class,
        BuffVisualOrbs.class,
        BuffVisualSetEntries.class,
        BuffVisuals.class,
        BuffVisualsArtVariations.class,
        Chanceableitemclasses.class,
        CharacterAudioEvents.class,
        CharacterCreationButton.class,
        CharacterCreationDialogue.class,
        CharacterEventTextAudio.class,
        CharacterMeleeSkills.class,
        CharacterPanelDescriptionModes.class,
        CharacterPanelStats.class,
        CharacterPanelTabs.class,
        CharacterStartQuestState.class,
        CharacterStartStateSet.class,
        CharacterStartStates.class,
        CharacterTextAudio.class,
        Characters.class,
        ChatIcons.class,
        ChestClusters.class,
        ChestEffects.class,
        Chests.class,
        ChieftainLifeScalingPerLevel.class,
        ClassPassiveSkillOverrides.class,
        ClientLakeDifficulty.class,
        ClientLeagueAction.class,
        ClientStrings.class,
        CloneShot.class,
        Colours.class,
        Commands.class,
        ComponentAttributeRequirements.class,
        ComponentCharges.class,
        ConditionalAchievements.class,
        CoreLeagues.class,
        CorpseSinkVariations.class,
        CorpseTypeTags.class,
        CosmeticsEquipPanelMode.class,
        CostTypes.class,
        CraftableModTypes.class,
        CraftingBenchOptions.class,
        CraftingBenchSortCategories.class,
        CraftingBenchSpecificOptionId.class,
        CraftingBenchTypes.class,
        CraftingBenchUnlockCategories.class,
        CraftingItemClassCategories.class,
        CrucibleDifficulty.class,
        CrucibleEndgameMonsterPacks.class,
        CrucibleLifeScalingPerLevel.class,
        CruciblePlayerClassOffsets.class,
        CrucibleTags.class,
        CurrencyExchange.class,
        CurrencyExchangeCategories.class,
        CurrencyItems.class,
        CurrencyStashTabLayout.class,
        CurrencyUseEffects.class,
        DaemonSpawningData.class,
        DamageEffectVariations.class,
        DamageHitEffects.class,
        DamageParticleEffects.class,
        DamageWhenHitEffects.class,
        Dances.class,
        DaressoPitFights.class,
        DefaultMonsterStats.class,
        DeliriumStashTabLayout.class,
        DelveAzuriteShop.class,
        DelveBiomes.class,
        DelveCatchupDepths.class,
        DelveCraftingModifierDescriptions.class,
        DelveCraftingModifiers.class,
        DelveCraftingTags.class,
        DelveDynamite.class,
        DelveFeatures.class,
        DelveFlares.class,
        DelveLevelScaling.class,
        DelveMonsterSpawners.class,
        DelveResourcePerLevel.class,
        DelveRewardTierConstants.class,
        DelveRobotVariations.class,
        DelveRooms.class,
        DelveStashTabLayout.class,
        DelveUpgrades.class,
        Descendancy.class,
        DescentExiles.class,
        DescentRewardChests.class,
        DescentStarterChest.class,
        DialogueEvent.class,
        DisplayMinionMonsterType.class,
        DivinationCardArt.class,
        DivinationCardStashTabLayout.class,
        DivinationWindowVisuals.class,
        Doors.class,
        DroneBaseTypes.class,
        DroneTypes.class,
        DropEffects.class,
        DropPool.class,
        EclipseMods.class,
        EffectDrivenSkill.class,
        EffectivenessCostConstants.class,
        EinharMissions.class,
        EinharPackFallback.class,
        ElderBossArenas.class,
        ElderGuardians.class,
        ElderMapBossOverride.class,
        EndgameCorruptionMods.class,
        EndlessLedgeChests.class,
        EntityInfobarStyle.class,
        EnvironmentTransitions.class,
        Environments.class,
        EssenceStashTabLayout.class,
        EssenceType.class,
        Essences.class,
        EvergreenAchievements.class,
        ExecuteGEAL.class,
        ExpandingPulse.class,
        ExpeditionAreas.class,
        ExpeditionBalancePerLevel.class,
        ExpeditionCurrency.class,
        ExpeditionDeals.class,
        ExpeditionFactions.class,
        ExpeditionMarkersCommon.class,
        ExpeditionNPCs.class,
        ExpeditionRelicMods.class,
        ExpeditionRelics.class,
        ExpeditionStorageLayout.class,
        ExpeditionTerrainFeatures.class,
        ExperienceLevels.class,
        ExplodingStormBuffs.class,
        ExtraTerrainFeatures.class,
        FixedHideoutDoodadTypes.class,
        FixedMissions.class,
        FlaskStashBaseTypeOrdering.class,
        Flasks.class,
        FlavourText.class,
        Footprints.class,
        FootstepAudio.class,
        FragmentStashTabLayout.class,
        GameConstants.class,
        GameLogos.class,
        GameObjectTasks.class,
        GameObjectTasksFromStats.class,
        GameStats.class,
        GamepadButton.class,
        GamepadButtonCombination.class,
        GamepadThumbstick.class,
        GamepadType.class,
        GemEffects.class,
        GemTags.class,
        GenericBuffAuras.class,
        GenericLeagueRewardTypeVisuals.class,
        GenericLeagueRewardTypes.class,
        GeometryAttack.class,
        GeometryChannel.class,
        GeometryProjectiles.class,
        GeometryTrigger.class,
        GiftWrapArtVariations.class,
        GlobalAudioConfig.class,
        GoldActScaling.class,
        GoldConstants.class,
        GoldModPrices.class,
        Grandmasters.class,
        GrantedEffectLabels.class,
        GrantedEffectQualityStats.class,
        GrantedEffectStatSets.class,
        GrantedEffectStatSetsPerLevel.class,
        GrantedEffects.class,
        GrantedEffectsPerLevel.class,
        GroundEffectTypes.class,
        GroundEffects.class,
        Harbingers.class,
        HardModeExtraContentChances.class,
        HardModeExtraContentChancesPerMapTier.class,
        HarvestCraftCostScalingByBasetype.class,
        HarvestCraftFilters.class,
        HarvestCraftOptionIcons.class,
        HarvestCraftOptions.class,
        HarvestCraftTiers.class,
        HarvestInfrastructure.class,
        HarvestLifeScalingPerLevel.class,
        HarvestPerLevelValues.class,
        HarvestSeedItems.class,
        HarvestSeeds.class,
        HeistAreaFormationLayout.class,
        HeistAreas.class,
        HeistBalancePerLevel.class,
        HeistChestRewardTypes.class,
        HeistChests.class,
        HeistChokepointFormation.class,
        HeistConstants.class,
        HeistContracts.class,
        HeistDoodadNPCs.class,
        HeistDoors.class,
        HeistEquipment.class,
        HeistGeneration.class,
        HeistIntroAreas.class,
        HeistJobs.class,
        HeistJobsExperiencePerLevel.class,
        HeistLockType.class,
        HeistNPCAuras.class,
        HeistNPCBlueprintTypes.class,
        HeistNPCDialogue.class,
        HeistNPCStats.class,
        HeistNPCs.class,
        HeistObjectiveValueDescriptions.class,
        HeistObjectives.class,
        HeistPatrolPacks.class,
        HeistQuestContracts.class,
        HeistRevealingNPCs.class,
        HeistRooms.class,
        HeistStorageLayout.class,
        HeistValueScaling.class,
        HellscapeAOReplacements.class,
        HellscapeAreaPacks.class,
        HellscapeExperienceLevels.class,
        HellscapeFactions.class,
        HellscapeImmuneMonsters.class,
        HellscapeItemModificationTiers.class,
        HellscapeLifeScalingPerLevel.class,
        HellscapeModificationInventoryLayout.class,
        HellscapeMods.class,
        HellscapeMonsterPacks.class,
        HellscapePassiveTree.class,
        HellscapePassives.class,
        HideoutCraftingBenchDoodads.class,
        HideoutCraftingBenchInterfaceVisuals.class,
        HideoutDoodadCategory.class,
        HideoutDoodadTags.class,
        HideoutDoodads.class,
        HideoutNPCs.class,
        HideoutRarity.class,
        HideoutStashDoodads.class,
        HideoutWaypointDoodads.class,
        Hideouts.class,
        HudEnergyShieldVisuals.class,
        HudLifeVisuals.class,
        HudVisualsFromStat.class,
        ImpactSoundData.class,
        Incubators.class,
        IncursionArchitect.class,
        IncursionBrackets.class,
        IncursionChestRewards.class,
        IncursionChests.class,
        IncursionRoomBossFightEvents.class,
        IncursionRooms.class,
        IncursionUniqueUpgradeComponents.class,
        IndexableSkillGems.class,
        IndexableSupportGems.class,
        InfluenceAmbushVariations.class,
        InfluenceExalts.class,
        InfluenceModUpgrades.class,
        InfluenceTags.class,
        InvasionMonsterRestrictions.class,
        InvasionMonstersPerArea.class,
        Inventories.class,
        ItemClassCategories.class,
        ItemClassPickupEffect.class,
        ItemClassVisualReplacement.class,
        ItemClasses.class,
        ItemCostPerLevel.class,
        ItemCosts.class,
        ItemExperiencePerLevel.class,
        ItemExperienceTypes.class,
        ItemFrameType.class,
        ItemInherentSkills.class,
        ItemNoteCode.class,
        ItemPickupEffect.class,
        ItemSpirit.class,
        ItemStances.class,
        ItemVisualEffect.class,
        ItemVisualHeldBodyModel.class,
        ItemVisualIdentity.class,
        ItemVisualReplacement.class,
        ItemisedCorpse.class,
        ItemisedNecropolisPacks.class,
        ItemisedVisualEffect.class,
        JobAssassinationSpawnerGroups.class,
        JobRaidBrackets.class,
        KeywordPopups.class,
        KillstreakThresholds.class,
        KiracLevels.class,
        LabyrinthAreas.class,
        LabyrinthBonusItems.class,
        LabyrinthCraftOptionTiers.class,
        LabyrinthCraftOptions.class,
        LabyrinthExclusionGroups.class,
        LabyrinthIzaroChests.class,
        LabyrinthNodeOverrides.class,
        LabyrinthRewardTypes.class,
        LabyrinthSecretEffects.class,
        LabyrinthSecrets.class,
        LabyrinthSection.class,
        LabyrinthSectionLayout.class,
        LabyrinthTrials.class,
        LabyrinthTrinkets.class,
        Labyrinths.class,
        LakeBossLifeScalingPerLevel.class,
        LakeMetaOptions.class,
        LakeMetaOptionsUnlockText.class,
        LakeRoomCompletion.class,
        LakeRooms.class,
        LeagueFlag.class,
        LeagueInfo.class,
        LeagueInfoPanelVersions.class,
        LeagueProgressQuestFlags.class,
        LeagueStaticRewards.class,
        LegacyAtlasInfluenceOutcomes.class,
        LegionBalancePerLevel.class,
        LegionChestCounts.class,
        LegionChestTypes.class,
        LegionFactions.class,
        LegionMonsterCounts.class,
        LegionMonsterVarieties.class,
        LegionRanks.class,
        LegionRewardTypeVisuals.class,
        LevelRelativePlayerScaling.class,
        MTXSetBonus.class,
        MagicMonsterLifeScalingPerLevel.class,
        MapCompletionAchievements.class,
        MapConnections.class,
        MapCreationInformation.class,
        MapDeviceRecipes.class,
        MapDevices.class,
        MapFragmentMods.class,
        MapInhabitants.class,
        MapPins.class,
        MapPurchaseCosts.class,
        MapSeries.class,
        MapSeriesTiers.class,
        MapStashSpecialTypeEntries.class,
        MapStashSubstashGroup.class,
        MapStashUniqueMapInfo.class,
        MapStatConditions.class,
        MapTierAchievements.class,
        MapTiers.class,
        Maps.class,
        MavenDialog.class,
        MavenFights.class,
        MavenJewelRadiusKeystones.class,
        Melee.class,
        MeleeTrails.class,
        MemoryLineType.class,
        MetamorphosisStashTabLayout.class,
        MicroMigrationData.class,
        MicrotransactionAppliedInventoryItemArtVariations.class,
        MicrotransactionCategory.class,
        MicrotransactionCharacterPortraitVariations.class,
        MicrotransactionChargeVariations.class,
        MicrotransactionCombineFormula.class,
        MicrotransactionConditionalApparitionEvents.class,
        MicrotransactionConditionalApparitions.class,
        MicrotransactionCursorVariations.class,
        MicrotransactionEquippedIconVariations.class,
        MicrotransactionFireworksVariations.class,
        MicrotransactionJewelVariations.class,
        MicrotransactionObjectEffects.class,
        MicrotransactionOnKillBeams.class,
        MicrotransactionOnKillConditions.class,
        MicrotransactionOnKillEffects.class,
        MicrotransactionPeriodicCharacterEffectVariations.class,
        MicrotransactionPortalVariations.class,
        MicrotransactionRarityDisplay.class,
        MicrotransactionRecycleOutcomes.class,
        MicrotransactionRecycleSalvageValues.class,
        MicrotransactionSkillGemEffectSlotTypes.class,
        MicrotransactionSlot.class,
        MicrotransactionSlotAdditionalDefaultOptions.class,
        MicrotransactionSocialFrameVariations.class,
        MiniQuestStates.class,
        MinimapIcons.class,
        MinionType.class,
        MiscAnimated.class,
        MiscAnimatedArtVariations.class,
        MiscBeams.class,
        MiscBeamsArtVariations.class,
        MiscEffectPacks.class,
        MiscEffectPacksArtVariations.class,
        MiscObjects.class,
        MiscObjectsArtVariations.class,
        MissionTimerTypes.class,
        MissionTransitionTiles.class,
        ModEffectStats.class,
        ModEquivalencies.class,
        ModFamily.class,
        ModSellPriceTypes.class,
        ModType.class,
        Mods.class,
        MonsterArmours.class,
        MonsterBonuses.class,
        MonsterConditionalEffectPacks.class,
        MonsterConditions.class,
        MonsterDeathAchievements.class,
        MonsterDeathConditions.class,
        MonsterGroupEntries.class,
        MonsterHeightBrackets.class,
        MonsterHeights.class,
        MonsterMapBossDifficulty.class,
        MonsterMapDifficulty.class,
        MonsterMortar.class,
        MonsterPackCounts.class,
        MonsterPackEntries.class,
        MonsterPacks.class,
        MonsterProjectileAttack.class,
        MonsterProjectileSpell.class,
        MonsterResistances.class,
        MonsterSegments.class,
        MonsterSpawnerGroups.class,
        MonsterSpawnerGroupsPerLevel.class,
        MonsterSpawnerOverrides.class,
        MonsterStatueOverrides.class,
        MonsterTypes.class,
        MonsterVarieties.class,
        MonsterVarietiesArtVariations.class,
        MouseCursorSizeSettings.class,
        MoveDaemon.class,
        MultiPartAchievementAreas.class,
        MultiPartAchievementConditions.class,
        MultiPartAchievements.class,
        Music.class,
        MusicCategories.class,
        MysteryBoxes.class,
        NPCAudio.class,
        NPCConversations.class,
        NPCDialogueStyles.class,
        NPCFollowerVariations.class,
        NPCMaster.class,
        NPCPortraits.class,
        NPCShop.class,
        NPCShopSets.class,
        NPCTalk.class,
        NPCTalkCategory.class,
        NPCTalkConsoleQuickActions.class,
        NPCTextAudio.class,
        NPCs.class,
        NearbyMonsterConditions.class,
        NecropolisCraftBases.class,
        NecropolisCraftItemTypes.class,
        NecropolisCraftingMods.class,
        NecropolisCraftingModsFromStats.class,
        NecropolisPackAdditionalPacks.class,
        NecropolisPackImplicitClass.class,
        NecropolisPackImplicits.class,
        NecropolisPackModTiers.class,
        NecropolisPackMods.class,
        NecropolisPacks.class,
        NecropolisPacksPerArea.class,
        NecropolisUniqueCrafts.class,
        NetTiers.class,
        Notifications.class,
        OnKillAchievements.class,
        PCBangRewardMicros.class,
        PVPTypes.class,
        PackFormation.class,
        PackFrequencyNames.class,
        PantheonPanelLayout.class,
        PantheonSouls.class,
        PassiveJewelArt.class,
        PassiveJewelNodeModifyingStats.class,
        PassiveJewelRadii.class,
        PassiveJewelSlots.class,
        PassiveOverrideLimits.class,
        PassiveSkillFilterCatagories.class,
        PassiveSkillFilterOptions.class,
        PassiveSkillMasteryEffects.class,
        PassiveSkillMasteryGroups.class,
        PassiveSkillOverrideTypes.class,
        PassiveSkillOverrides.class,
        PassiveSkillStatCategories.class,
        PassiveSkillTattooTargetSets.class,
        PassiveSkillTattoos.class,
        PassiveSkillTreeTutorial.class,
        PassiveSkillTreeUIArt.class,
        PassiveSkillTrees.class,
        PassiveSkills.class,
        PassiveTreeExpansionJewelSizes.class,
        PassiveTreeExpansionJewels.class,
        PassiveTreeExpansionSkills.class,
        PassiveTreeExpansionSpecialSkills.class,
        Pet.class,
        PlayerConditions.class,
        PlayerTradeWhisperFormats.class,
        PortalAudio.class,
        PortalAudioEvents.class,
        PreloadFromStats.class,
        PreloadGroups.class,
        PrimordialBossLifeScalingPerLevel.class,
        Projectiles.class,
        ProjectilesArtVariations.class,
        Quest.class,
        QuestAchievements.class,
        QuestFlags.class,
        QuestItems.class,
        QuestRewardOffers.class,
        QuestRewards.class,
        QuestStates.class,
        QuestStaticRewards.class,
        QuestTrackerGroup.class,
        QuestType.class,
        RaceTimes.class,
        Races.class,
        RareMonsterLifeScalingPerLevel.class,
        Rarity.class,
        Realms.class,
        RecipeUnlockDisplay.class,
        RecipeUnlockObjects.class,
        RelicInventoryLayout.class,
        RelicItemEffectVariations.class,
        ReminderText.class,
        RitualBalancePerLevel.class,
        RitualConstants.class,
        RitualRuneTypes.class,
        RitualSetKillAchievements.class,
        RitualSpawnPatterns.class,
        RogueExileLifeScalingPerLevel.class,
        RogueExiles.class,
        Rulesets.class,
        RunicCircles.class,
        SafehouseBYOCrafting.class,
        SafehouseCraftingSpreeCurrencies.class,
        SafehouseCraftingSpreeType.class,
        SalvageBoxes.class,
        SanctumAirlocks.class,
        SanctumDefenceIcons.class,
        SanctumDeferredRewardCategories.class,
        SanctumDeferredRewards.class,
        SanctumFloors.class,
        SanctumFodderLifeScalingPerLevel.class,
        SanctumLifeScalingPerLevel.class,
        SanctumPersistentEffectCategories.class,
        SanctumPersistentEffects.class,
        SanctumRoomTypes.class,
        SanctumRooms.class,
        SanctumSelectionDisplayOverride.class,
        ScarabTypes.class,
        Scarabs.class,
        ScoutingReports.class,
        SentinelCraftingCurrency.class,
        SentinelDroneInventoryLayout.class,
        SentinelPassiveStats.class,
        SentinelPassiveTypes.class,
        SentinelPassives.class,
        SentinelPowerExpLevels.class,
        SentinelStorageLayout.class,
        SentinelTaggedMonsterStats.class,
        SessionQuestFlags.class,
        ShaperGuardians.class,
        ShieldTypes.class,
        ShopTag.class,
        ShrineSounds.class,
        ShrineVisualArtVariations.class,
        Shrines.class,
        SigilDisplay.class,
        SingleGroundLaser.class,
        SkillArtVariations.class,
        SkillCraftingData.class,
        SkillGemInfo.class,
        SkillGemLevelUpEffects.class,
        SkillGemSupports.class,
        SkillGems.class,
        SkillMineVariations.class,
        SkillMorphDisplay.class,
        SkillSurgeEffects.class,
        SkillTotemVariations.class,
        SkillTrapVariations.class,
        SkillWeaponEffects.class,
        SocketNotches.class,
        SoulCores.class,
        SoundEffects.class,
        SpawnAdditionalChestsOrClusters.class,
        SpawnObject.class,
        SpecialRooms.class,
        SpecialTiles.class,
        SpectreOverrides.class,
        StampChoice.class,
        StampFamily.class,
        StartingPassiveSkills.class,
        StashTabAffinities.class,
        StashType.class,
        StatDescriptionFunctions.class,
        StatVisuals.class,
        StatisticTrackingMicrotransactions.class,
        StatisticTrackingMicrotransactionsStatistics.class,
        Stats.class,
        StatsAffectingGeneration.class,
        StatsFromSkillStats.class,
        Strongboxes.class,
        SuicideExplosion.class,
        SummonedSpecificBarrels.class,
        SummonedSpecificMonsters.class,
        SummonedSpecificMonstersOnDeath.class,
        SummonedSpecificMonstersOnDeathStats.class,
        SupportGems.class,
        SurgeEffects.class,
        SurgeTypes.class,
        SynthesisAreaSize.class,
        SynthesisAreas.class,
        SynthesisBonuses.class,
        SynthesisBrackets.class,
        SynthesisFragmentDialogue.class,
        SynthesisGlobalMods.class,
        SynthesisMonsterExperiencePerLevel.class,
        SynthesisRewardCategories.class,
        SynthesisRewardTypes.class,
        TableCharge.class,
        TableMonsterSpawners.class,
        Tags.class,
        TalismanMonsterMods.class,
        TalismanPacks.class,
        Talismans.class,
        TalkingPetAudioEvents.class,
        TalkingPetNPCAudio.class,
        TalkingPets.class,
        TencentAutoLootPetCurrencies.class,
        TencentAutoLootPetCurrenciesExcludable.class,
        TerrainPlugins.class,
        TieredMicrotransactions.class,
        TieredMicrotransactionsVisuals.class,
        Tinctures.class,
        Tips.class,
        Topologies.class,
        TormentSpirits.class,
        TormentedSpiritLifeScalingPerLevel.class,
        TradeMarketCategory.class,
        TradeMarketCategoryGroups.class,
        TradeMarketCategoryListAllClass.class,
        TradeMarketImplicitModDisplay.class,
        TradeMarketIndexItemAs.class,
        TriggerBeam.class,
        TriggerSpawners.class,
        TryTheNewLeagueVersions.class,
        Tutorial.class,
        TypeTags.class,
        UITalkText.class,
        UltimatumEncounterTypes.class,
        UltimatumEncounters.class,
        UltimatumItemisedRewards.class,
        UltimatumLifescalingPerLevel.class,
        UltimatumMapModifiers.class,
        UltimatumModifierTypes.class,
        UltimatumModifiers.class,
        UltimatumTrialMasterAudio.class,
        UncutGemAdditionalTiers.class,
        UncutGemTiers.class,
        UniqueChests.class,
        UniqueJewelLimits.class,
        UniqueMaps.class,
        UniqueStashLayout.class,
        UniqueStashTypes.class,
        UtilityFlaskBuffs.class,
        VillageAssignWorkerTextAudio.class,
        VillageExports.class,
        VillageFarmAdjacency.class,
        VillageJobTypes.class,
        VillageJobs.class,
        VillageResources.class,
        VillageSharedConstants.class,
        VillageUniqueDisenchantValues.class,
        VillageUpgradeCategories.class,
        VillageUpgrades.class,
        VirtualStatContextFlags.class,
        VoteState.class,
        VoteType.class,
        WarbandsGraph.class,
        WarbandsMapGraph.class,
        WarbandsPackMonsters.class,
        WarbandsPackNumbers.class,
        WeaponClasses.class,
        WeaponImpactSoundData.class,
        WeaponPassiveSkillTypes.class,
        WeaponPassiveSkills.class,
        WeaponPassiveTreeBalancePerItemLevel.class,
        WeaponPassiveTreeUniqueBaseTypes.class,
        WeaponTypes.class,
        WindowCursors.class,
        Words.class,
        WorldAreaLeagueChances.class,
        WorldAreas.class,
        WorldPopupIconTypes.class,
            SkillGemsForUniqueStat.class
    );
}
