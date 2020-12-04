package harmonised.pmmo.gui;

import harmonised.pmmo.config.JType;
import harmonised.pmmo.util.XP;
import harmonised.pmmo.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class TileButton extends GuiButton
{
    private final ResourceLocation items = XP.getResLoc( Reference.MOD_ID, "textures/gui/items.png" );
    private final ResourceLocation items2 = XP.getResLoc( Reference.MOD_ID, "textures/gui/items2.png" );
    private final ResourceLocation buttons = XP.getResLoc( Reference.MOD_ID, "textures/gui/buttons.png" );
    public int elementOne;
    public int offsetOne;
    public int elementTwo;
    public int offsetTwo;
    public int index;
    public int page = 0;
    public String transKey;
    public JType jType;

    public TileButton(int posX, int posY, int elementOne, int elementTwo, String transKey, JType jType, IPressable onPress )
    {
        super(posX, posY, 32, 32, "", onPress);

        this.jType = jType;

        this.elementOne = elementOne * 32;
        this.elementTwo = elementTwo * 32;
        this.transKey = transKey;

        if( elementOne > 23 )
            offsetOne = 192;
        else if( elementOne > 15 )
            offsetOne = 128;
        else if( elementOne > 7 )
            offsetOne = 64;
        else
            offsetOne = 0;

        if( elementTwo >= 32 )
        {
            page = 1;
            elementTwo -= 32;
        }

        if( elementTwo > 23 )
            offsetTwo = 192;
        else if( elementTwo > 15 )
            offsetTwo = 128;
        else if( elementTwo > 7 )
            offsetTwo = 64;
        else
            offsetTwo = 0;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, double p_renderButton_3_)
    {
        Minecraft minecraft = Minecraft.getMinecraft();
//        FontRenderer fontrenderer = minecraft.fontRenderer;
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, this.alpha);
//        int i = this.getYImage(this.hovered);
        GlStateManager.enableBlend();
        GlStateManager.defaultBlendFunc();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        minecraft.getTextureManager().bindTexture( buttons );
        this.drawTexturedModalRect(this.x, this.y, this.offsetOne + ( this.hovered ? 32 : 0 ), this.elementOne, this.width, this.height );
        minecraft.getTextureManager().bindTexture( page == 0 ? items : items2 );
        this.drawTexturedModalRect(this.x, this.y, this.offsetTwo + ( this.hovered ? 32 : 0 ), this.elementTwo, this.width, this.height );
        this.renderBg(minecraft, p_renderButton_1_, p_renderButton_2_);
//        int j = getFGColor();
//        this.drawCenteredString(fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
    }
}