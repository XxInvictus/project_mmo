package harmonised.pmmo.gui;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import harmonised.pmmo.config.JType;
import harmonised.pmmo.util.XP;
import harmonised.pmmo.util.Reference;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PrefsScreen extends Screen
{
    private final List<IGuiEventListener> children = Lists.newArrayList();
    private final ResourceLocation box = XP.getResLoc( Reference.MOD_ID, "textures/gui/screenboxy.png" );
    private final ResourceLocation logo = XP.getResLoc( Reference.MOD_ID, "textures/gui/logo.png" );
    private static TileButton exitButton;

    MainWindow sr = Minecraft.getInstance().getMainWindow();;
    private int boxWidth = 256;
    private int boxHeight = 256;
    private int x;
    private int y;
    private List<TileButton> tileButtons;
    private UUID uuid;

    public PrefsScreen( UUID uuid, ITextComponent titleIn )
    {
        super(titleIn);
        this.uuid = uuid;
        GlossaryScreen.history = new ArrayList<>();
    }

//    @Override
//    public boolean isPauseScreen()
//    {
//        return false;
//    }

    @Override
    protected void init()
    {
        tileButtons = new ArrayList<>();

        x = ( (sr.getScaledWidth() / 2) - (boxWidth / 2) );
        y = ( (sr.getScaledHeight() / 2) - (boxHeight / 2) );

        exitButton = new TileButton(x + boxWidth - 24, y - 8, 7, 0, "pmmo.exit", "", (something) ->
        {
            Minecraft.getInstance().displayGuiScreen( new MainScreen( uuid, new TranslationTextComponent( "pmmo.stats" ) ) );
        });

        TileButton ironButton = new TileButton( x + 24 + 36, y + 24 + 36 * 5, 1, 0, "pmmo.ironTitle","", button ->
        {
            Minecraft.getInstance().displayGuiScreen( new ScrollScreen( uuid, new TranslationTextComponent( ((TileButton) button).transKey ), JType.DONATOR_IRON, Minecraft.getInstance().player ) );
        });

        TileButton dandelionButton = new TileButton( x + 24 + 36 * 5, y + 24 + 36 * 5, 1, 0, "pmmo.dandelionTitle","", button ->
        {
            Minecraft.getInstance().displayGuiScreen( new ScrollScreen( uuid, new TranslationTextComponent( ((TileButton) button).transKey ), JType.DONATOR_DANDELION, Minecraft.getInstance().player ) );
        });

        TileButton lapisButton = new TileButton( (int) (x + 24 + 36 * 2.5), y + 24 + 36, 1, 0, "pmmo.lapisTitle","", button ->
        {
            Minecraft.getInstance().displayGuiScreen( new ScrollScreen( uuid, new TranslationTextComponent( ((TileButton) button).transKey ), JType.DONATOR_LAPIS, Minecraft.getInstance().player ) );
        });

        addButton(exitButton);
        tileButtons.add( ironButton );
        tileButtons.add( dandelionButton );
        tileButtons.add( lapisButton );

        for( TileButton button : tileButtons )
        {
            addButton( button );
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks)
    {
        renderBackground( 1 );
        super.render(mouseX, mouseY, partialTicks);

        x = ( (sr.getScaledWidth() / 2) - (boxWidth / 2) );
        y = ( (sr.getScaledHeight() / 2) - (boxHeight / 2) );

//        fillGradient(x + 20, y + 52, x + 232, y + 164, 0x22444444, 0x33222222);

        for( TileButton button : tileButtons )
        {
            if( mouseX > button.x && mouseY > button.y && mouseX < button.x + 32 && mouseY < button.y + 32 )
                renderTooltip( new TranslationTextComponent( button.transKey ).getFormattedText(), mouseX, mouseY );
        }

        RenderSystem.enableBlend();
        Minecraft.getInstance().getTextureManager().bindTexture( logo );
        this.blit( sr.getScaledWidth() / 2 - 100, sr.getScaledHeight() / 2 - 80, 0, 0,  200, 60 );
    }

    @Override
    public void renderBackground(int p_renderBackground_1_)
    {
        if (this.minecraft != null)
        {
            this.fillGradient(0, 0, this.width, this.height, 0x66222222, 0x66333333 );
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.BackgroundDrawnEvent(this));
        }
        else
            this.renderDirtBackground(p_renderBackground_1_);


        boxHeight = 256;
        boxWidth = 256;
        Minecraft.getInstance().getTextureManager().bindTexture( box );
        RenderSystem.disableBlend();
        this.blit( x, y, 0, 0,  boxWidth, boxHeight );
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scroll)
    {
        return super.mouseScrolled(mouseX, mouseY, scroll);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        if( button == 1 )
        {
            exitButton.onPress();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button)
    {
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY)
    {
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

}