package com.ventivu.CustomStuff3Plugin.baubles;

import com.ventivu.CustomStuff3Plugin.baubles.Windows.WrappedBaubles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class BauItemRender implements IItemRenderer {
    private static final ResourceLocation field_110930_b = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private final WrappedBaubles wrappedItem;
    public BauItemRender(WrappedBaubles item) {
        wrappedItem=item;
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.EQUIPPED;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return helper != ItemRendererHelper.BLOCK_3D;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (type == ItemRenderType.EQUIPPED) {
            EntityLivingBase entity = (EntityLivingBase)data[1];
            IIcon icon = entity.getItemIcon(item, 0);
            if (icon == null) {
                GL11.glPopMatrix();
            } else {
                Tessellator tessellator = Tessellator.instance;
                float f = icon.getMinU();
                float f1 = icon.getMaxU();
                float f2 = icon.getMinV();
                float f3 = icon.getMaxV();
                GL11.glEnable(32826);
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                GL11.glTranslatef(0.0F, -0.3F, 0.0F);
                GL11.glScalef(1.5F, 1.5F, 1.5F);
                GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScalef(1F, 1F, 1.0F);
                GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
                ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
                GL11.glDisable(32826);
                if (item.hasEffect(0)) {
                    TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
                    GL11.glDepthFunc(514);
                    GL11.glDisable(2896);
                    texturemanager.bindTexture(field_110930_b);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(768, 1);
                    float f7 = 0.76F;
                    GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
                    GL11.glMatrixMode(5890);
                    GL11.glPushMatrix();
                    float f8 = 0.125F;
                    GL11.glScalef(f8, f8, f8);
                    float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
                    GL11.glTranslatef(f9, 0.0F, 0.0F);
                    GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                    ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glScalef(f8, f8, f8);
                    f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                    GL11.glTranslatef(-f9, 0.0F, 0.0F);
                    GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                    ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                    GL11.glPopMatrix();
                    GL11.glMatrixMode(5888);
                    GL11.glDisable(3042);
                    GL11.glEnable(2896);
                    GL11.glDepthFunc(515);
                }

            }
        }
    }
}
