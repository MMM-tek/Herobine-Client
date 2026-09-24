package net.minecraft.client;

import net.minecraft.util.ResourceLocation;
import net.lax1dude.eaglercraft.opengl.WorldRenderer;
import net.lax1dude.eaglercraft.opengl.GlStateManager;
import net.lax1dude.eaglercraft.opengl.RealOpenGLEnums;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MinecraftError;

public class LoadingScreenRenderer implements IProgressUpdate {
	private String message = "";
	private final Minecraft mc;
	private String currentlyDisplayedText = "";
	private long systemTime = Minecraft.getSystemTime();
	private boolean loadingSuccess;

	public LoadingScreenRenderer(Minecraft mcIn) {
		this.mc = mcIn;
	}

	public void resetProgressAndMessage(String message) {
		this.loadingSuccess = false;
		this.displayString(message);
	}

	public void displaySavingString(String message) {
		this.loadingSuccess = true;
		this.displayString(message);
	}

	private void displayString(String message) {
		this.currentlyDisplayedText = message;

		if (!this.mc.running) {
			if (!this.loadingSuccess) {
				throw new MinecraftError();
			}
		} else {
			GlStateManager.clear(256);
			GlStateManager.matrixMode(5889);
			GlStateManager.loadIdentity();

			GlStateManager.ortho(0.0D, mc.scaledResolution.getScaledWidth_double(),
					mc.scaledResolution.getScaledHeight_double(), 0.0D, 100.0D, 300.0D);

			GlStateManager.matrixMode(5888);
			GlStateManager.loadIdentity();
			GlStateManager.translate(0.0F, 0.0F, -200.0F);
		}
	}

	public void displayLoadingString(String message) {
		if (!this.mc.running) {
			if (!this.loadingSuccess) {
				throw new MinecraftError();
			}
		} else {
			this.systemTime = 0L;
			this.message = message;
			this.setLoadingProgress(-1);
			this.systemTime = 0L;
		}
	}

	public void setLoadingProgress(int progress) {
		if (!this.mc.running) {
			if (!this.loadingSuccess) {
				throw new MinecraftError();
			}
		} else {
			long i = Minecraft.getSystemTime();

			if (i - this.systemTime >= 100L) {
				this.systemTime = i;
				ScaledResolution scaledresolution = mc.scaledResolution;
				int k = scaledresolution.getScaledWidth();
				int l = scaledresolution.getScaledHeight();

				GlStateManager.clear(256);

				GlStateManager.matrixMode(5889);
				GlStateManager.loadIdentity();
				GlStateManager.ortho(0.0D, scaledresolution.getScaledWidth_double(),
						scaledresolution.getScaledHeight_double(), 0.0D, 100.0D, 300.0D);
				GlStateManager.matrixMode(5888);
				GlStateManager.loadIdentity();
				GlStateManager.translate(0.0F, 0.0F, -200.0F);

				GlStateManager.clear(16640);

				Tessellator tessellator = Tessellator.getInstance();
				WorldRenderer bufferbuilder = tessellator.getBuffer();

				GlStateManager.disableTexture2D();
				bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
				bufferbuilder.pos(0.0D, (double) l, 0.0D).color(0, 0, 0, 255).endVertex();
				bufferbuilder.pos((double) k, (double) l, 0.0D).color(0, 0, 0, 255).endVertex();
				bufferbuilder.pos((double) k, 0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
				bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
				tessellator.draw();

				GlStateManager.enableTexture2D();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

				this.mc.getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/gui/title/herobrine.png"));

				int imageWidth = 256;
				int imageHeight = 256;

				double posX = (k - imageWidth) / 2.0D;
				double posY = (l / 2.0D) - (imageHeight / 2.0D) - 55;

				bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
				bufferbuilder.pos(posX, posY + imageHeight, 0.0D).tex(0.0D, 1.0D).color(255, 255, 255, 255).endVertex();
				bufferbuilder.pos(posX + imageWidth, posY + imageHeight, 0.0D).tex(1.0D, 1.0D).color(255, 255, 255, 255).endVertex();
				bufferbuilder.pos(posX + imageWidth, posY, 0.0D).tex(1.0D, 0.0D).color(255, 255, 255, 255).endVertex();
				bufferbuilder.pos(posX, posY, 0.0D).tex(0.0D, 0.0D).color(255, 255, 255, 255).endVertex();
				tessellator.draw();

				if (progress >= 0) {
					int k1 = k / 2 - 50;
					int l1 = l / 2 + 16;
					GlStateManager.disableTexture2D();
					bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
					bufferbuilder.pos((double) k1, (double) l1, 0.0D).color(128, 128, 128, 255).endVertex();
					bufferbuilder.pos((double) k1, (double) (l1 + 2), 0.0D).color(128, 128, 128, 255).endVertex();
					bufferbuilder.pos((double) (k1 + 100), (double) (l1 + 2), 0.0D).color(128, 128, 128, 255).endVertex();
					bufferbuilder.pos((double) (k1 + 100), (double) l1, 0.0D).color(128, 128, 128, 255).endVertex();
					bufferbuilder.pos((double) k1, (double) l1, 0.0D).color(128, 255, 128, 255).endVertex();
					bufferbuilder.pos((double) k1, (double) (l1 + 2), 0.0D).color(128, 255, 128, 255).endVertex();
					bufferbuilder.pos((double) (k1 + progress), (double) (l1 + 2), 0.0D).color(128, 255, 128, 255).endVertex();
					bufferbuilder.pos((double) (k1 + progress), (double) l1, 0.0D).color(128, 255, 128, 255).endVertex();
					tessellator.draw();
					GlStateManager.enableTexture2D();
				}

				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(RealOpenGLEnums.GL_SRC_ALPHA,
						RealOpenGLEnums.GL_ONE_MINUS_SRC_ALPHA, RealOpenGLEnums.GL_ONE, RealOpenGLEnums.GL_ZERO);
				this.mc.fontRendererObj.drawStringWithShadow(this.currentlyDisplayedText,
						(float) ((k - this.mc.fontRendererObj.getStringWidth(this.currentlyDisplayedText)) / 2),
						(float) (l / 2 - 4 - 16), 16777215);
				this.mc.fontRendererObj.drawStringWithShadow(this.message,
						(float) ((k - this.mc.fontRendererObj.getStringWidth(this.message)) / 2),
						(float) (l / 2 - 4 + 8), 16777215);

				this.mc.updateDisplay();
			}
		}
	}

	public void setDoneWorking() {
	}

	public void eaglerShow(String line1, String line2) {
		if (!this.mc.running) {
			if (!this.loadingSuccess) {
				throw new MinecraftError();
			}
		} else {
			this.systemTime = 0L;
			this.currentlyDisplayedText = line1;
			this.message = line2;
			this.setLoadingProgress(-1);
			this.systemTime = 0L;
		}
	}
}